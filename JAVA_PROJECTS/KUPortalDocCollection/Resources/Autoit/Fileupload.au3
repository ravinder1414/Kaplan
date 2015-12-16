WinWait("File Upload","",10)
WinActivate("File Upload")
if WinExists("File Upload") Then
       Send("C:\Java Projects\JAVA_PROJECTS\KHEC Portal\Resources\invalid.png")
       Sleep(1000)
       Send("{ENTER}")


EndIf