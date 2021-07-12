# Minecraft Sprite Helper
![Logo](/readme/msh_logo_centered.png)
#### Draw without switching between hundreds of sprites

## Status

This is work-in-progress. The export option does work, but there's no default spritesheet. This is too being worked on. 
Latest update: I frankly have no willpower to work on this, and I'm also moving away from Minecraft to something more open-source. Maybe one day. 

## Basic idea
In 1.5 Minecraft's texture system moved from 256x256px atlases to individual 16x16px textures, but while that allowed for more precise and robust resource pack work, the drawbacks I noticed were losses in consistency of quality and palettes due to not knowing how different textures actually look side-by-side, and in productivity, having to work with large amounts of files. 

This is a quality-of-life Java app made to help artists (and anyone, really) manage that. 

## How does it work?
**For _import_ option**, it takes a bulk of individual textures and stitches them together in a texture atlas meant to be edited. <br>
**For _export_ option**, it takes the texture atlas plus name list and cuts it into individual textures titled accordingly to entries in that text file. 

## Credits
- **[Mojang](https://minecraft.net/)** - Original assets, of course
- **[6LeoMC](https://github.com/yzl210/)** - All the code (thank you so much for this)
- **[temhotaokeaha](https://github.com/temhotaokeaha/)** - (not so) Original idea and other stuff
