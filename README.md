# 💥 ExploteFuit

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://minecraft.net)
[![Fabric API](https://img.shields.io/badge/Fabric%20API-required-blue.svg)](https://fabricmc.net/)
[![License](https://img.shields.io/badge/License-CC0-lightgrey.svg)](LICENSE)

&gt; A chaotic Minecraft Fabric mod that adds explosive consequences to eating certain fruits. Think twice before snacking!

## 🎮 Overview

**ExploteFuit** is a Minecraft mod built for **Fabric 1.21.1** that introduces an explosive twist to Minecraft's food mechanics. When players consume specific fruits added by this mod, they trigger explosions—turning a simple meal into a dangerous gamble.

## ✨ Features

- **Explosive Fruits**: New food items that cause explosions when eaten
- **High Risk, High Reward**: Fruits may provide beneficial effects alongside the explosion
- **Multiplayer Chaos**: Perfect for pranking friends or creating unexpected PvP scenarios
- **Lightweight**: Minimal performance impact using Fabric API

## 📋 Requirements

- **Minecraft**: 1.21.1
- **Fabric Loader**: &gt;= 0.16.0
- **Fabric API**: Required
- **Java**: 21 or higher

## 🚀 Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.21.1
2. Download the latest **ExploteFuit** release from [GitHub Releases](../../releases)
3. Place the `.jar` file in your `mods` folder:
   - **Windows**: `%appdata%/.minecraft/mods`
   - **Mac**: `~/Library/Application Support/minecraft/mods`
   - **Linux**: `~/.minecraft/mods`
4. Launch Minecraft with Fabric profile

## 🍎 Usage

### Explosive Fruits

| Item | Effect | Cooldown |
|------|--------|----------|
| *Coming Soon* | Explosion on consumption | TBD |

*Note: Specific fruit types and their effects will be documented as the mod develops.*

## ⚙️ Configuration

Configuration files can be found in:
config/explotefuit.json
Available options:
- `explosionPower`: Adjust explosion strength (default: 2.0)
- `damagePlayer`: Whether explosions damage the player (default: true)
- `destroyBlocks`: Block destruction toggle (default: false for griefing protection)

## 🛠️ Building from Source

```bash
# Clone the repository
git clone https://github.com/Cesar-Plyed/ExploteFuit.git
cd ExploteFuit

# Build the mod
./gradlew build

# Output will be in build/libs/
