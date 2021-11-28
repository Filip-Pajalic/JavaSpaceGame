# JavaSpaceGame
Learning Java by making a game.

## Todo in order

- <strike>Replicate original controls and movement in ECS. </strike>
- <strike>Implement MovableSystem. </strike>
- <strike>Add Observer/subject event system  </strike>
- <strike>Add new graphics and shapes. </strike>
- <strike>Add debug mode </strike>
- <strike> Add Hitboxes to rendering for debugging </strike>  
- <strike> Added bases for gui class </strike>
- <strike> Add text for debugging </strike>
- <strike> Print FPS for debugging </strike>
- <strike>Add rectangular/circular collision </strike>
- <strike> Add random enemy spawn </strike>
- <strike> Add scoring system </strike>

Basic:

- fix wobbly render? (scaling problem?)
- Fix problem at 0.0 position
- Add startmenu
- Add animation when flying
- Add particle effects from rockets
- Add particle effects from target
- Add entities to collisionlist, so collisions are not checked twice, Collide only once with each unique object
- Add ControlSupport for phone
- Add level + leveltimer and highscore
- Ensure targets dont spawn to close to each other


Improvements:
- Refactor boilerplate code  
- Procedural generation of background and ground
- Make use of components updateSystem
- Prioritize rendering i.e background first. Change rendersystem.
- Figure out how to resize properly in resize.
- Add sprite animation support
- Draw vectors in debug
- Add Proper vector math
- Add maxspeed and other configurations   
- Break out entity configurations to a file that is loaded on programstart. 
- Optimize code to maximize fps
- Add targetManager/enemy.
- Find way to implement streams and lambdas for practice.
- Reduce boilerplate code
- Make editor
- replace observer with async?
- Replace edgechecking with infinite scroll and line collision to the ground.
