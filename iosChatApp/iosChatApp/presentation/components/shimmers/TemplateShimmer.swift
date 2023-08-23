//
//  TemplateShimmer.swift
//  iosChatApp
//
//  Created by Zaryab on 28/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TemplateShimmer: View {
    var body: some View {
        RoundedRectangle(cornerRadius: 20)
            .padding(.horizontal, 10)
            .frame(minWidth: 0,maxWidth: .infinity,minHeight: 100,maxHeight: 100, alignment: .leading)
            .foregroundColor(Color.theme.card)
            .shadow(radius: 5)
    }
}

