# Chat-GPT Application - Kotlin Multiplatform Mobile (KMM)

A cross-platform mobile application built with Kotlin Multiplatform Mobile (KMM) that provides an AI-powered chat interface using OpenAI's GPT API. The app features categorized templates, real-time chat functionality, and a modern UI for both Android and iOS platforms.

## 🚀 Features

- **Cross-Platform**: Native Android and iOS apps sharing business logic
- **AI Chat Interface**: Integration with OpenAI's GPT API for intelligent conversations
- **Template System**: Pre-defined chat templates across various categories
- **Category Filtering**: Organized content by categories (Education, Business, Health, etc.)
- **Modern UI**: Clean, responsive design with SwiftUI (iOS) and Jetpack Compose (Android)
- **Real-time Messaging**: Live chat experience with typing indicators
- **Remote Configuration**: Dynamic content management through remote config

## 📱 Supported Platforms

- **Android**: API level 24+ (Android 7.0+)
- **iOS**: iOS 13.0+
- **Shared Logic**: Kotlin Multiplatform

## 🏗️ Architecture

### Project Structure

```
ChatApplication/
├── androidChatApp/          # Android-specific code
│   ├── src/main/java/       # Android activities and UI
│   └── build.gradle.kts     # Android build configuration
├── iosChatApp/              # iOS-specific code
│   ├── iosChatApp/          # SwiftUI views and iOS logic
│   └── iosChatApp.xcodeproj # Xcode project configuration
├── shared/                  # Shared Kotlin Multiplatform code
│   ├── src/
│   │   ├── commonMain/      # Common business logic
│   │   ├── androidMain/     # Android-specific implementations
│   │   └── iosMain/         # iOS-specific implementations
│   └── build.gradle.kts     # Shared module configuration
├── buildSrc/                # Build configuration and dependencies
└── gradle/                  # Gradle wrapper and properties
```

### Architecture Pattern

The application follows **Clean Architecture** principles with clear separation of concerns:

- **Presentation Layer**: SwiftUI (iOS) and Jetpack Compose (Android)
- **Domain Layer**: Business logic, use cases, and domain models
- **Data Layer**: Network services, data sources, and mappers
- **Dependency Injection**: Modular dependency management

### Key Components

#### Shared Module (`shared/`)
- **Network Layer**: HTTP client using Ktor for API communication
- **Domain Models**: Chat, Message, Template, Category entities
- **Use Cases**: QueryChat, LoadData, ChangeCategory interactors
- **Data Sources**: Remote config and OpenAI API integration
- **State Management**: Reactive state handling with Kotlin Flows

#### Android App (`androidChatApp/`)
- **UI Framework**: Jetpack Compose
- **Main Activity**: Entry point with Compose integration
- **Theme**: Material Design components

#### iOS App (`iosChatApp/`)
- **UI Framework**: SwiftUI
- **Navigation**: NavigationView with custom routing
- **Components**: Reusable UI components (MessageView, CategoryChip, etc.)
- **Assets**: Custom fonts (Poppins) and color schemes

## 🛠️ Technology Stack

### Core Technologies
- **Kotlin Multiplatform Mobile (KMM)**: Cross-platform business logic
- **Kotlin**: 1.8.0
- **Gradle**: 7.4.0

### Android
- **Jetpack Compose**: Modern declarative UI toolkit
- **Android SDK**: API 24-33
- **Material Design**: UI components and theming

### iOS
- **SwiftUI**: Declarative UI framework
- **iOS SDK**: 13.0+
- **Custom Fonts**: Poppins typography

### Networking & APIs
- **Ktor**: HTTP client for API communication
- **OpenAI API**: GPT integration for chat functionality
- **JSON Serialization**: Kotlinx serialization

### Development Tools
- **Android Studio**: Android development
- **Xcode**: iOS development
- **Gradle**: Build automation
- **CocoaPods**: iOS dependency management

## 📋 Prerequisites

Before running the project, ensure you have:

- **Android Studio** (latest stable version)
- **Xcode** (latest version for iOS development)
- **JDK 8** or higher
- **Kotlin** 1.8.0+
- **Gradle** 7.4.0+
- **OpenAI API Key** (for chat functionality)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Chat-GPT-Application-KMM-master
```

### 2. Configure API Keys

#### For OpenAI Integration:
1. Obtain an API key from [OpenAI](https://platform.openai.com/api-keys)
2. Add your API key to the appropriate configuration file
3. Update the API endpoint if needed in `ChatServiceImpl.kt`

### 3. Build and Run

#### Android
```bash
# Build the shared module
./gradlew :shared:build

# Build and run Android app
./gradlew :androidChatApp:assembleDebug
```

#### iOS
```bash
# Build the shared framework
./gradlew :shared:linkDebugFrameworkIosX64

# Open Xcode project
open iosChatApp/iosChatApp.xcodeproj

# Build and run in Xcode
```

### 4. Development Setup

#### Android Development
1. Open the project in Android Studio
2. Sync Gradle files
3. Select the `androidChatApp` run configuration
4. Run on device or emulator

#### iOS Development
1. Open `iosChatApp/iosChatApp.xcodeproj` in Xcode
2. Select your target device or simulator
3. Build and run the project

## 🔧 Configuration

### Build Configuration

The project uses centralized build configuration in `buildSrc/`:

- **Application.kt**: App version, SDK versions, and identifiers
- **Dependencies.kt**: Version catalogs for all dependencies
- **Plugins.kt**: Plugin version management

### Environment Setup

#### Gradle Properties
```properties
# Memory allocation
org.gradle.jvmargs=-Xmx2048M -Dfile.encoding=UTF-8

# Kotlin configuration
kotlin.code.style=official

# Android configuration
android.useAndroidX=true
android.nonTransitiveRClass=true

# Multiplatform configuration
kotlin.mpp.enableCInteropCommonization=true
```

## 📚 API Integration

### OpenAI API

The application integrates with OpenAI's GPT API for chat functionality:

- **Endpoint**: `https://api.openai.com/v1/completions`
- **Authentication**: Bearer token authentication
- **Model**: `text-davinci-003` (configurable)
- **Request Format**: JSON with prompt, model, and parameters

### Remote Configuration

Dynamic content management through remote configuration:
- **Templates**: Pre-defined chat templates
- **Categories**: Content categorization
- **Configuration**: Runtime parameter updates

## 🎨 UI/UX Features

### Design System
- **Typography**: Poppins font family
- **Color Scheme**: Custom color palette with theme support
- **Components**: Reusable UI components
- **Animations**: Smooth transitions and loading states

### Key Screens
1. **Home Screen**: Category selection and template browsing
2. **Chat Screen**: Real-time conversation interface
3. **Loading States**: Shimmer effects and progress indicators

## 🧪 Testing

### Running Tests

```bash
# Run all tests
./gradlew test

# Run Android tests
./gradlew :androidChatApp:test

# Run shared module tests
./gradlew :shared:test
```

## 📦 Dependencies

### Shared Module
- **Ktor**: HTTP client and networking
- **Kotlinx Coroutines**: Asynchronous programming
- **Kotlinx Serialization**: JSON serialization

### Android
- **Jetpack Compose**: UI toolkit
- **Activity Compose**: Compose integration
- **Material Design**: UI components

### iOS
- **SwiftUI**: Native iOS UI framework
- **Foundation**: Core iOS frameworks

## 🔒 Security Considerations

- **API Key Management**: Store API keys securely
- **Network Security**: HTTPS-only communication
- **Data Privacy**: No local storage of sensitive data
- **Input Validation**: Sanitize user inputs

## 🚀 Deployment

### Android
1. Generate signed APK/AAB
2. Upload to Google Play Store
3. Configure app signing and release management

### iOS
1. Archive the app in Xcode
2. Upload to App Store Connect
3. Submit for App Store review

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Troubleshooting

### Common Issues

#### Build Failures
- Ensure all dependencies are properly installed
- Check Kotlin and Gradle versions
- Clean and rebuild the project

#### iOS Build Issues
- Verify Xcode version compatibility
- Check CocoaPods installation
- Clean derived data in Xcode

#### API Integration Issues
- Verify API key configuration
- Check network connectivity
- Review API rate limits

### Getting Help

- Check the [Issues](../../issues) section
- Review the [Wiki](../../wiki) for detailed documentation
- Contact the maintainers for support

## 📈 Future Enhancements

- [ ] Offline mode support
- [ ] Chat history persistence
- [ ] Multiple AI model support
- [ ] Voice input/output
- [ ] Image generation integration
- [ ] User authentication
- [ ] Chat export functionality
- [ ] Custom theme support

## 👥 Team

- **Zaryab**: Project Creator and Maintainer
- **Contributors**: See [Contributors](../../graphs/contributors) page

---

**Note**: This application requires an OpenAI API key to function. Make sure to configure your API credentials before running the app.
