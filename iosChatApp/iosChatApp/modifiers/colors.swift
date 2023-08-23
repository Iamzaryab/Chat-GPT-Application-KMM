//
//  colors.swift
//  iosChatApp
//
//  Created by Zaryab on 28/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
extension Color {
    static let theme = ColorTheme()
    static let launch = LaunchTheme()
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}
struct LaunchTheme {
    let background = Color("LaunchBackgroundColor")
}
struct ColorTheme {
    let background = Color("background")
    let messageFrom = Color("message_from")
    let messageMe = Color("message_me")
    let textTitle = Color("text_title")
    let textDescription = Color("text_description")
    let categoryBg = Color("category")
    let selectedCategoryBg = Color("category_selected")
    let card = Color("card")
}
