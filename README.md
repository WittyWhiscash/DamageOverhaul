# Damage Overhaul

Damage Overhaul is a mod for Minecraft 1.16.2 and higher aiming to add damage typing and change the armor and damage calculation formula. Simply put though, it recalculates armor based on the amount of damage, and how spread out the damage is amongst typing. A larger damage hit will render much more profoundly on an entity than many small typings, and armor will feel less effective on bigger hits. This all feeds into an exponential decay calculation to make armor less effective the more you have of it against a bigger attack. The aim of this is to make it feel like larger attacks feel more profound, while with armor, you can shrug off smaller hits. This mod is extremely customizable, from being able to modify variables in the calculations, to being able to define your own damage types, to being able to modify what damage a tool deals. This all occurs through YAML files in the configuration directory.

***

This mod is Fabric only. I am not considering a Forge port at this time. If you want to port it, it's licensed under GPL-3, so have a go as long as you credit me and license it under GPL as well.

***


It does this through a few targeted Mixins into the LivingEntity and PlayerEntity classes. While these mixins are required in order to make the custom damage calculations, these mixins are slightly invasive, and **THIS WILL RENDER SOME MODS INCOMPATIBLE WITH THIS MOD.** As I find incompatibilities with this mod and others as raised through issues, I will mark them incompatible on my end.
 
 #### Marked Incompatible:
 - None yet!
 
***

### Required Mods:
*Duh.*
- Fabric Loader: 0.10.8 or greater

*Required for the custom particles this mod adds as indicators.*
- Fabric API: 0.29.3 or greater

*Required as part of the config screen this mod generates.*
- Cloth Config API (Fabric): 4.8.3 or greater

### Suggested Mods:
*Suggested for many reasons, but that includes the mods screen, and configuring the mod in-game.*
- Mod Menu: 1.15.0 or greater

*Suggested for the guidebook, which is currently incomplete, but not necessary.*
- Patchouli: 1.16.2-44-FABRIC or greater  