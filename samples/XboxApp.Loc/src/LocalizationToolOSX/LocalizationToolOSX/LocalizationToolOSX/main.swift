//
//  main.swift
//  LocalizationToolOSX
//
//  Created by Ryan Wagoner on 3/21/16.
//  Copyright © 2016 Xbox. All rights reserved.
//

import Foundation

let stderr = NSFileHandle.fileHandleWithStandardError()
let stdout = NSFileHandle.fileHandleWithStandardOutput()
let whitespace = NSCharacterSet.whitespaceAndNewlineCharacterSet()

public func writeToStdError(str: String) {
    if let data = str.dataUsingEncoding(NSUTF8StringEncoding) {
        stderr.writeData(data)
    }
}

public func writeToStdOut(str: String) {
    if let data = str.dataUsingEncoding(NSUTF8StringEncoding) {
        stdout.writeData(data)
    }
}

public func parseLocComment(str :String) -> String {
    do {
        let regex = try NSRegularExpression(pattern: "_locComment=\".+?\"", options: NSRegularExpressionOptions.CaseInsensitive)
        let nsstr = str as NSString
        let matches = regex.matchesInString(str, options: NSMatchingOptions(rawValue: 0), range: NSMakeRange(0, nsstr.length)) as Array<NSTextCheckingResult>
        
        for match in matches {
            let locCommentAll = nsstr.substringWithRange(match.range)
            let locCommentCommentOnly = locCommentAll.substringWithRange(Range(start: locCommentAll.startIndex.advancedBy(13), end: locCommentAll.endIndex.advancedBy(-1)))
            let iOS = locCommentCommentOnly.rangeOfString("iOS")
            
            if (iOS == nil) {
                // not an iOS string, lets ignore it
                return ""
            } else {
                return locCommentCommentOnly
            }
        }
    } catch {
        writeToStdError("\(error)")
    }
        
    return ""
}

var inputFile:String
var outputFile:String

// Process the command line arguments
if Process.arguments.count != 3 {
    writeToStdError("USAGE: LocalizationToolOSX <InputFileName> <OutputFileName>\n")
    writeToStdError("REQUIRES 2 arguments. Recieved \(Process.arguments.count - 1)\n")
    //exit(EXIT_FAILURE)
    
    inputFile = "/Users/ryanwa/XboxGit/XboxApp.iOS/XboxApp.Loc/Xbox.Smartglass.Loc.xml"
    outputFile = "/Users/ryanwa/XboxGit/XboxApp.iOS/XboxApp.Loc/TEMP/localizable.strings"
} else {
    inputFile = Process.arguments[1]
    outputFile = Process.arguments[2]
}

writeToStdOut("Input File is \(inputFile)\n")
writeToStdOut("Output File is \(outputFile)\n")

// Attempt to open the file handle
do {
    let xmlString = try String(contentsOfFile: inputFile)
    var locString = "    //\n    //Version 1.0\n    //\n"
    let xml = SWXMLHash.parse(xmlString)
    
    for elem in xml["Resource"]["String"] {
        let resId = elem.element!.attributes["ResID"]
        let resValue = elem.element!.text
        let resComment = elem.element!.comment
        
        if (resValue != nil && resComment != nil) {
            let escapedValue = resValue!.stringByReplacingOccurrencesOfString("\"", withString: "\\\"")
            let locComment = parseLocComment(resComment!)
            
            if (locComment != "") {
                locString.appendContentsOf("    /* \(locComment) */\n")
                locString.appendContentsOf("    \"\(resId!)\" = \"\(escapedValue)\";\n")
            }
        }
    }
    
    do {
        try locString.writeToFile(outputFile, atomically: true, encoding: NSUTF8StringEncoding)
    } catch {
        writeToStdError("\(error)")
        writeToStdError("Unable to write file \(outputFile)")
        exit(EXIT_FAILURE)
    }
    
} catch {
    writeToStdError("Unable to read file \(inputFile)\n")
    exit(EXIT_FAILURE)
}

exit(EXIT_SUCCESS)

