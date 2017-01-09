using Microsoft.TeamFoundation.Client;
using Microsoft.TeamFoundation.VersionControl.Client;
using System;
using System.IO;
using System.Linq;

namespace LocalizationToolUI
{
    public class TfsHelper
    {
        private readonly Uri tfsServerUrl;

        public TfsHelper(Uri tfsServerUrl)
        {
            this.tfsServerUrl = tfsServerUrl;
        }        

        public static bool IsLatestFileVersion(string filePath)
        {
            return true;
        }

        public bool ResolveFile(string filePath)
        {
            return true;
        }

        public void CheckOutFile(string filePath)
        {
            if (string.IsNullOrWhiteSpace(filePath) || !File.Exists(filePath))
            {
                throw new Exception("string xml file location is invalid or doesn't exist");
            }
            using (var tfs = TfsTeamProjectCollectionFactory.GetTeamProjectCollection(this.tfsServerUrl))
            {
                var service = tfs.GetService<VersionControlServer>();

                var workspace = service.GetWorkspace(filePath);

                workspace.Get(new string[] {filePath}, VersionSpec.Latest, RecursionType.None, GetOptions.None);
                if (workspace.PendEdit(filePath) != 1)
                {
                    throw new Exception("Error in checkout");
                }
            }
        }

        public int CheckInFile(string filePath)
        {
            if (File.Exists(filePath))
            {
                using (var tfs = TfsTeamProjectCollectionFactory.GetTeamProjectCollection(this.tfsServerUrl))
                {
                    var service = tfs.GetService<VersionControlServer>();
                    var workspace = service.GetWorkspace(filePath);

                    var conflicts = workspace.QueryConflicts(new string[] {filePath}, true);
                    if (conflicts.Any())
                    {
                        var resolvedConflicts = new Conflict[conflicts.Length];
                        foreach (Conflict conflict in conflicts)
                        {
                            workspace.ResolveConflict(conflict, out resolvedConflicts);
                        }

                        if (resolvedConflicts.Any(resolvedConflict => !resolvedConflict.IsResolved))
                        {
                            throw new Exception(
                                "Local copy of file saved but conflicts encountered during checkin - Merge and check in manually");
                        }
                    }

                    var pendingChanges = workspace.GetPendingChanges(filePath);
                    if (pendingChanges.Count() == 1)
                    {
                        return workspace.CheckIn(pendingChanges, "loc file checked in from localization tool");
                    }
                }

                throw new Exception("No pending changes - please check out file in first step");
            }

            return -1;
        }

        public void UndoCheckout(string filePath)
        {
            if (File.Exists(filePath))
            {
                using (var tfs = TfsTeamProjectCollectionFactory.GetTeamProjectCollection(this.tfsServerUrl))
                {
                    var service = tfs.GetService<VersionControlServer>();
                    var workspace = service.GetWorkspace(filePath);

                    var pendingChanges = workspace.GetPendingChanges(filePath);
                    if (pendingChanges.Count() == 1)
                        workspace.Undo(filePath);
                }
            }
        }       
    }
}
