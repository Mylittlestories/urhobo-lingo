# GitHub + APK + Play Store Publishing Guide

## GitHub Push
```bash
cd urhobo-lingo
git init
git add .
git commit -m "UrhoboLingo final hardening"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/urhobo-lingo.git
git push -u origin main
```

## Build debug APK locally
```bash
./gradlew assembleDebug
```
Output:
```text
app/build/outputs/apk/debug/app-debug.apk
```

## Build release APK locally
1. Copy `release/signing.properties.example` to `release/signing.properties`
2. Create your keystore
3. Update signing values
4. Run:
```bash
./gradlew assembleRelease
```

## Build with GitHub Actions
- Push repo to GitHub
- Open Actions tab
- Run `Android CI Build` or `Release APK Build`
- Download the generated APK artifact

## Commit real APK into repo
If you specifically want the APK binary inside the repository:
```bash
cp app/build/outputs/apk/debug/app-debug.apk release/urhobo-lingo-debug.apk
git add release/urhobo-lingo-debug.apk
git commit -m "Add generated debug APK"
git push
```

## Important
Only commit a real generated APK. Do not use placeholder files.
