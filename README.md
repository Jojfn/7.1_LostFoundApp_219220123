# 🕵️ Lost and Found App – SIT708 Task 7.1

A robust Android application developed as part of SIT708, designed to help users report lost items or list things they have found to facilitate their return.

## ✨ Features

- 📝 **Create Adverts:** Easily post details about a lost or found item, including a description, date, and location.
- 👤 **Contact Info:** Include your phone number so others can reach out immediately.
- 📋 **View All Items:** A comprehensive list of all current adverts managed via `RecyclerView`.
- 🔍 **Detailed View:** Click on any item in the list to view its full details on a dedicated screen.
- 🗑️ **Remove Items:** Delete an advert once the item has been successfully recovered or returned.
- 💾 **Local Persistence:** All data is stored in an SQLite database, ensuring it persists even after closing the app.
- 🎨 **Material Design:** A clean, intuitive user interface for a seamless user experience.

---

## 🚀 How to Run the Project

### Prerequisites

- 🛠️ **Android Studio** (latest version)
- 📱 **Android SDK** (API 24 or higher)
- ☕ **Java 8+**

### Steps

1. **📥 Clone the repository**
   ```bash
   git clone https://github.com/your-username/LostAndFound.git

2. 📂 Open in Android Studio

**File** → **Open** → select the cloned folder `71`

3. 🔄 Sync Gradle

Click **File** → **Sync Project with Gradle Files** (or the 🐘 elephant icon)

4. 🏗️ Build the project

**Build** → **Make Project** (`Ctrl+F9`)

5. ▶️ Run the app

- Connect a physical device (USB debugging enabled) or start an emulator (API 24+)
- Click the green **Run** button (▶️)

6. 🎮 Use the app

- **Create Advert:** Click "Create a New Advert", fill in the item details, and click "Submit".
- **Show Items:** Click "Show All Items" to see a list of everything reported.
- **Item Detail:** Tap an item in the list to see more info.
- **Remove:** From the detail screen, click "Remove" if the item is no longer missing.
