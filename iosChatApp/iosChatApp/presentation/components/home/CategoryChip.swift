//
//  CategoryChip.swift
//  iosChatApp
//
//  Created by Zaryab on 27/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CategoryChip: View {
    private let title: String
    private let isSelected: Bool
    
    init(title: String, isSelected: Bool) {
        self.title = title
        self.isSelected = isSelected
    }
    
    var body: some View {
        PoppinsText(text: title, size: 14)
            .font(.subheadline)
            .padding(.horizontal, 10)
            .padding(.vertical, 5)
            .background(isSelected ? Color.theme.selectedCategoryBg: Color.theme.categoryBg)
            .foregroundColor(.white)
            .cornerRadius(10)
    }
}

