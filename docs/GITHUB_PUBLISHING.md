# GitHub Publishing

This repository is ready for GitHub.

## After pushing
- GitHub Actions will build a debug APK artifact if Gradle and Android SDK resolve correctly.
- Download the APK from the workflow artifacts page.

## Notes
- To commit an APK into the repo itself, first build it successfully on a machine with Android SDK/Gradle, then add the generated APK file.
- Committing APK binaries to git is possible, but usually artifacts/releases are better than storing binaries in the main branch.
