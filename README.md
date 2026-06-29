# UrhoboLingo

UrhoboLingo is a Duolingo-inspired Android language-learning app for learning **Urhobo** with lessons, quizzes, vocabulary, progress tracking, and an **English ↔ Urhobo** translator.

## Highlights
- Kotlin + Jetpack Compose
- Onboarding
- Learning path
- Interactive lessons and quiz flow
- Vocabulary with save and speak actions
- Translator
- Local persistence
- Room-ready scaffolding
- GitHub Actions APK automation

## Real APK without Android Studio
You asked for APK generation without Android Studio.

That is supported through **GitHub Actions** in this repo.
After pushing to GitHub, the workflows can build a real APK in the cloud.

### Workflows
- `.github/workflows/android.yml` → builds APK artifact
- `.github/workflows/release-apk.yml` → builds APK and publishes it to GitHub Releases on tag push

## Output
Expected debug APK path during build:
```text
app/build/outputs/apk/debug/app-debug.apk
```

## Voice note
There is currently no confirmed real female Urhobo TTS integrated in this project. The best professional path is native-speaker recorded audio for pronunciation, with optional English narration for app guidance.
