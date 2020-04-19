# ROVR Assessment Android Kotlin

## Clone

```bash
git clone https://github.com/alimaisam/rovr-assessment-android.git
```

## Pre-requisite

- Android SDK 23 and above
- Android Emulator

## To Get Data from API on Android Emulator

- Change BASE_URL in app.java.com.alimaisam.githubsearch.Helper.Endpoints.kt

## Start

- Wait for the gradle to finish building the project

- Running on device: 
    - Just click play on Android studio
    
- Running on Emulator: 
```
Start emulator with this command from terminal 
$ emulator @<EMULATOR-NAME> -dns-server 8.8.8.8
```

## Limitation

* Used JSON file to read and write data instead of any database
* Unit test only Search Model