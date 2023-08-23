//
//  CategoryChipShimmer.swift
//  iosChatApp
//
//  Created by Zaryab on 30/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CategoryChipShimmer: View {
    var body: some View {
        Rectangle()
            .frame(width: 40,height: 25)
            .padding(.horizontal, 10)
            .padding(.vertical, 5)
            .background(Color.theme.categoryBg)
            .foregroundColor(Color.theme.categoryBg)
            .cornerRadius(10)

    }
}
