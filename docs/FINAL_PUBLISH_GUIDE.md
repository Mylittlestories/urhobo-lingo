# Final Push, Commit, Publish, and APK Guide

## 1. Commit locally
```bash
cd urhobo-lingo
git add .
git commit -m "Fix CI build and add voice guidance"
```

## 2. Ensure remote is correct
```bash
git remote -v
```
Use your real repo URL only.

## 3. Push
To replace placeholder remote content:
```bash
git push -u origin main --force
```

## 4. Build APK without Android Studio
This repo is configured to build in GitHub Actions using hosted Gradle + Android SDK.
- Open GitHub repo
- Click `Actions`
- Run `Android CI Build`
- Download artifact `urhobo-lingo-debug-apk`

## 5. Publish Release APK
```bash
git tag v1.1.0
git push origin v1.1.0
```
GitHub will run `Release APK to GitHub Releases` and attach the built APK.

## 6. Optional: commit the real APK into repo
After downloading artifact:
```bash
copy app-debug.apk release\urhobo-lingo-debug.apk
git add release\urhobo-lingo-debug.apk
git commit -m "Add real generated APK"
git push
```
