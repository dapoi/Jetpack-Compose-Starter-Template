# Jetpack Compose Starter Template

A starter template for Android development using Jetpack Compose with Nav3, following best practices and modularization.

## Highlights
- **Jetpack Compose UI** - Fully built using Jetpack Compose for a modern UI experience.
- **Modularization** - Separation of concerns with a structured module-based architecture.
- **Best Practices** - Implements MVVM, Clean Architecture, and other industry standards.

## Project Structure
```
project-root/
├── app/                # Main application module
├── core/common/        # Common utilities, components, and helpers
├── core/data/          # Data handling (repository, API, database)
│   ├── api/            # (Recommended) Separate module if you have multiple services
├── core/navigation/    # Navigation handling module
├── feature/home/       # Home feature module
├── feature/info/       # Info feature module
├── build-logic/        # Gradle convention plugins
```

## Prerequisites
- Android Studio Quail
- JDK 21+
- Gradle 9.5.1

## Use This Template
This repository is set up as a template! 🚀 Click the **"Use this template"** button on GitHub to create your own repository based on this starter template.
> ![image](https://github.com/user-attachments/assets/504f2c84-f260-4c94-9123-705f1cf30d86)

## Available Technologies in the Template
- Hilt (Dependency Injection)
- Navigation 3
- Coroutine & Flow
- Retrofit
- Moshi
- DataStore
- Room Database

## 🧰 Android Module Generator
This project comes with a CLI script to auto-generate new modules in a snap.

### ⚙️ How To Use
Make the script executable:
```bash
chmod +x android-module-generator.sh
```

Run the generator:
```bash
./android-module-generator.sh
```

## 🚀 Example

```
📦 Base package (example: com.project.app): com.example.app
📁 Parent folder (example: feature): feature
🧩 Module name (example: home): profile
```

Generates:

```
feature/
└── profile/
    ├── build.gradle.kts
    └── src/
        └── main/
            └── java/com/example/app/feature/profile
```

and automatically updates `settings.gradle.kts`.


## Contribution
Contributions are welcome! Feel free to fork the repository and submit a pull request.

## ⭐ Support
If you find this project helpful, please consider giving it a star ⭐ on GitHub. Your support is greatly appreciated!
