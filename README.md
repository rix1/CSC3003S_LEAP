CSC3003S_LEAP
=============

Private repo for the CSC3003S software development project.

### Dependencies

This should cover pretty much anything you need to set up the developer environment.

  * [Java Development Kit 7+ (JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  * [Eclipse](http://www.eclipse.org/downloads/), the "Eclipse IDE for Java Developers" is sufficient.
  * [Android SDK](http://developer.android.com/sdk/installing.html), you only need the SDK, not the ADT bundle, which includes Eclipse. Install API level 18 and 19 (For Android 4.3 and 4.4.2) via the SDK Manager! See step below on how to do it.

  * [JGit plugin to Eclipse](http://eclipse.org/egit/download/) Use this update site: http://download.eclipse.org/egit/updates
  * [Android Development Tools for Eclipse](http://developer.android.com/tools/sdk/eclipse-adt.html), aka ADT Plugin. Use this update site: https://dl-ssl.google.com/android/eclipse/
  * [Eclipse Integration Gradle](https://github.com/spring-projects/eclipse-integration-gradle/), use this update site: http://dist.springsource.com/release/TOOLS/gradle


### Set up project on Windows:

Once Java Development Kit, Eclipse and the Android SDK is downloaded, install JDK and Eclipse, and move the 'android-sdk-[some platform/version number]/' folder to a place where it can stay permanently and won't get in the way.

For the three last bullet points, *repeat* the following process to install plugins to Eclipse:
		
Click the menu bar: 
		
	Help > Install New Software...
		
On the right hand select:

	Add...
		
Enter a name of your choice, and paste in the link above (the one stated after 'Use this update site:'). And push:
		
	OK
		
After the add-ons have loaded in the field below, click 

	Select All, or tick each package*	
	*For the Gradle installation, you only need to tick the second box

Click next and agree to the terms and conditions before you install the software. This might take some time depending on your network speed.

When all is installed, restart Eclipse. 

You are now ready to clone from Github. Because I have yet to try the JGit plugin myself, I don't know the exact specifics here. Will fill in as soon as I figure out.