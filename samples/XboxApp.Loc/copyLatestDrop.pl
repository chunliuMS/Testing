#!D:\Perl\bin\perl

use File::Copy;

my $latestDropDirectory = './LatestDrop';
opendir DH, $latestDropDirectory or die "Cannot open $latestDropDirectory: $!";

my @files = grep { ! -d } readdir DH;
closedir DH;

foreach (@files)
{
	copy("./LatestDrop/" . $_ . "/Xbox.Smartglass.loc.xml", "./Xbox.Smartglass." . $_ . ".Loc.xml") or die "Copy failed: $!";
	print("copying ./LatestDrop/" . $_ . "/Xbox.Smartglass.loc.xml to ./Xbox.Smartglass." . $_ . ".Loc.xml\n");
}