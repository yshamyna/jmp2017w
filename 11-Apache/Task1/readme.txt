How set up web app:
1) run "gradle clean war zipStaticsite zipApache";
2) go to build folder where customwebsite.war, and apache.zip, and staticsite.zip can be found;
3) deploy WAR to tomcat;
4) unzip apache.zip to [APACHE_HTTP_SERVER_HOME] directory -> click 'Override all';
5) create floder 'staticsite' in [APACHE_HTTP_SERVER_HOME] directory and unzip staticsite.zip there;
6) run apache HTTP server and Tomcat.