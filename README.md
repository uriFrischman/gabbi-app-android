# gAPPi

A native Android app that simplifies the process of assigning tasks to participants of synagogue services. 

# Libraries Used (so far)
  - Android SDK
  - AWS SDK (DynamoDB, Lambda)
  - Gson
  - Guava
  - SnappyDB
  - AutofitTextView
  
# Glossary of Terms
**Gabbi:** Coordinator of Synagogue Services
**Torah:** Old Testement
**Sefer:** Book of the Bible
**Bereishit:** Book of Genesis
**Shemot:** Book of Exodus
**Vayikra:** Book of Leviticus
**Bamidbar:** Book of Numbers
**Devarim:** Book of Deutoronomy
**Pasuk/Psukim:** Verse / Verses
**Perek:** Chapter
**Aliyah:** Bible Portion
**Zmanim:** Times 

# Summary of Features
## Task Distribution:
###   Current Problem: 
The way tasks get distributed in synagogues is unorganized. It consists of many back and forth emails, text messages and face-to-face encounters. There is no central source of information that everyone can access and become informed from.
### Solution:
A central database that all parties have access to. Based on status and position, a user can select tasks and assign them to him or herself. In other words, a JIRA ticketing-like interface that is personalized and optimized for use in synagogues.

## Access to Aliyah Text
###   Current Problem: 
Aliyahs (Bible potions) are read each Saturday and on Jewish holidays. This reading must be very precise and practiced well in advance. Currently, if a reader is assigned an aliyah to read, he or she will not have immediate access to its text until he or she consults a Bible.
### Solution:
By having the whole Bible stored in the app, even in offline situations, the user will be able to access any range of text from the Bible. This means that the reader can practice and prepare his or her reading on the go!

## Zmanim Tab
Jewish prayers have specific ranges of time that they can be performed. These times are calculated based on Jewish Law and are published on various websites each day. The zmanim tab will fetch this data and present it in an intuitive and simple interface.

## Ability to Donate to Synagogue
Synagogues are primarily sustained by membership dues and donations. This feature will be a payment portal that enables users to send donations directly from the application without the hassle of navigating synagogue websites.

# App Architecture
A custom `Application` class exists to set various utilites up before `MainActivity` is created.

The app consists of many `util` classes that keep functionality organized and clear. The following `util` classes are currently being used:
+ `AWSCognitoUtil.java`
+ `AWSDynamoDBUtil.java`
+ `AliyahUtil.java`
+ `DateUtil.java`
+ `EventUtil.java`
+ `FileUtil.java`
+ `SnappyDBUtil.java`
+ `StringUtil.java`
+ `TorahUtil.java`
+ `UserUtil.java`

So far the only UI element (list of events) is being housed in a `Fragment` as opposed to its `Activity`. This is the strategy going forward because it ensures that the components can be truly dynamic and utilized on demand with relative ease.

The data provided to the app is being provided by a `DynamoDB` call inside a `Loader`. So far the only display of this data is through a `RecyclerView`. 

Android's `DataBinding` feature is being utilized. This makes declaring and finding views very simple.

Models are created using Plain Old Java Objects and `DynamoDB` attributes where applicable.
