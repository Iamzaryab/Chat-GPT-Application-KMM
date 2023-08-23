//
//  IOSApp.swift
//  iosChatApp
//
//  Created by Zaryab on 25/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import SwiftUI
import shared
import FirebaseCore
import FirebaseRemoteConfig

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
    FirebaseApp.configure()


    return true
  }
}


@main
struct iOSApp: App {
    private let remoteConfigModule = RemoteConfigModule()
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    var body: some Scene {
		WindowGroup {
            HomeScreen(remoteConfigModule: remoteConfigModule)
		}
	}
}



