James Tan
Laura Seo

1. Install XAMPP Server
2. Go into XAMPP phpMyAdmin and create a database called 'db2'
3. Populate the database with tables and data using your own SQL statements or use the SQL file from PhaseII
4. Extract the contents of the .ZIP file (submission) until you end up with a single folder titled JamesTan_LauraSeo_PhaseIII
6. Go inside of that folder and drag and drop the entire inner folder 'DB2Mobile' into xampp/htdocs
** Alternatively, you can go into xampp/htdocs from your command terminal and execute git clone https://github.com/JamesTanGit/DB2Mobile.git
7. Open up Android Studio
8. Click on 'Open an existing Android Studio project'
9. Select the 'DB2Mobile' folder located in xampp/htdocs
10. *IMPORTANT STEP* In the project file explorer on the left side of Android Studio, go
into app -> java -> com.example.db2mobile -> SetIP.java and replace the String
variable 'IpAddress' with a String value representing your own IP address. This can
be found by going to your command terminal and typing in the command 'ipconfig' and
looking at the value next to 'IPv4 Address'. If this step is skipped, the PHP files
will not be retreived from the app.
11. Run the app by going to the top of Android Studio and clicking Run -> 'Run app' using your own device 
or an emulated device