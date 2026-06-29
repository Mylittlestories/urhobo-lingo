# UrhoboLingo Product + Engineering Plan

## Product Goal
Build a playful, modern Android app for learning **Urhobo**, inspired by Duolingo-style progression, while also providing an **English ↔ Urhobo** translator.

## What is delivered now
- Android Studio project
- Compose UI
- onboarding
- dashboard
- learning path
- lessons
- quiz flow
- vocabulary
- translator
- local persistence
- TTS helper
- Room/Firebase-ready structure
- publishing docs

## Architecture
- **Presentation:** Jetpack Compose
- **Navigation:** Navigation Compose
- **State:** `AppViewModel`
- **Data Access:** `AppRepository`
- **Persistence now:** SharedPreferences
- **Persistence next:** Room + DataStore
- **Cloud next:** Firebase Auth + Firestore

## Why this stack
Jetpack Compose is the best current Android-first option for a fast-moving product like this. It reduces UI boilerplate and is easier to evolve into a polished app.

## Roadmap
### Phase 1 — MVP Foundation
- Seed content
- Translator
- Quiz lessons
- Progress tracking
- Saved vocabulary

### Phase 2 — Core Learning Quality
- Native-speaker reviewed content
- Better lesson path and skill tree
- review mistakes mode
- adaptive difficulty
- listening and pronunciation tasks
- spaced repetition

### Phase 3 — Platform Growth
- sign-in and sync
- push notifications
- leaderboard
- referral loop
- CMS for admins/content editors

### Phase 4 — Store and Scale
- store assets
- privacy policy
- release signing
- crash reporting
- analytics
- subscription/monetization if desired

## APK / Release Note
A real release-ready APK or AAB requires:
- installed Android SDK
- Gradle wrapper
- signing config / keystore for release
- successful dependency resolution

## GitHub note
I cannot publish to GitHub directly without access. I can prepare all files and commands, and if you connect access later I can help finalize it.
