# Intro_to_AI

# Assignment 1: Avengers and Infinity Stone V1.

## Programming Language

```
Java (JDK 1 7 ) 
```
## Task

According to the _Marvel Comics_ , _Thanos_ is looking for the _Infinity Stones_ to terminate
half of the living creatures in the Universe, and _Avengers_ at the same time try to prevent this.
In one of the Marvel’s alternative realities Thanos tries to find one of the Infinity Stones.
Currently, he has no stones collected and he just recognized the location of the first one. This
stone is located on a very far planet. Thanos is a very strong creature, however, without _Infinity
Gauntlet_ filled with Infinity Stones his power is very limited. Therefore, he tries to avoid the
Avengers sent to the same planet and aiming not to give Thanos to realize his ambitions. The
location of the Avengers is unknown to him, but he also knows that only 4 of them were able
to achieve this planet: _Captain Marvel_ , _Captain America_ , _Thor_ and _Hulk_. Therefore, it may be
completely unsafe to follow the expected shortest path, Thanos may die.

Unintentionally Thanos met with Captain America and had a fight with him, after which
both of them were exhausted, Captain America even lost his _sa_ made of vibranium after
throwing it aiming to hit Thanos. The vibranium is famous for its extraordinary abilities to
absorb, store, and release large amounts of kinetic energy. Because of exhaustive fight Captain
America was delivered back to the spaceship by other Avengers. The remaining 3 Avengers
decided to go the different places of the planet to look for Thanos, because they had no idea
where the Infinity Stone could be located. Thanos understands that each of the remaining
Avengers is powerful enough to make him stuck in one more exhaustive fight. Therefore, his
strategy is to avoid his enemies. Each of his enemies is having different power.

Thor is a God of Thunder from _Asgard_ which is owning the _Stormbreaker_ and _Mjölnir_ ,
sentient enchanted weapons created out of _Uru_ metal forged with the heat of a dying star in the
Dwarven kingdom of _Nidavellir_. These weapons are enhancing the power of Thor and can even
kill Thanos. To defend himself at least from long distance attacks of Stormbreaker and Mjölnir
Thanos has to find the lost Shield of Captain America. Defeating Thor by Thanos is impossible
without Infinity Gauntlet_._ That is the reason to avoid a fight with former. However, finding of
the Shield is not a requirement, because it may increase the path. But in some cases, it may
extremely helpful.

Hulk is a green-skinned, hulking and muscular humanoid possessing a limitless degree
of physical strength. His power and speed are limited only by the level of its anger. Obviously,
defeat of Captain America made him very angry on Thanos. Hulk prefers close combats, but
for ranged combats he uses hand-clapping creating shock waves. He is almost immortal, but
can be defeated by cutting his head. Still, because of reincarnation ability Hulk will survive,
but decapitation can neutralize him till the end of the game. To make this possible some really
strong metal has to be applied, and Shield of Captain America made of vibranium is an option.

Captain Marvel is superhumanly strong thanks both to her hybrid organism and to her
ability of enhancing her physique by absorbing energy. The Shield of Captain America is
useless in fight against her and the best thing to do for Thanos is just not to get too close to
Captain Marvel.

You need to help Thanos to find the Infinity Stone, which location is known to only to
Thanos. Avengers are static enemies located somewhere on the map. Your environment is 9*
square lattice, which represents a partial map of a planet (see Figure 1).

## Thanos

You always start from top left map corner. Your goal is to find the shortest path till
Infinity Stone. Your ability to perceive troubles is defined in the “ _variants section”_ below.
Your algorithms will work on both variants. Thanos can move one step per turn horizontally
or vertically. Thanos is also called Actor in this assignment context.

## Thor

Thor is randomly generated on the map except inside the positions of Shield, Infinity
Stone, Hulk, Captain Marvel and where Thanos starts. Thor can perceive Thanos by throwing
the Stormbreaker and Mjölnir to consecutive cells (Moore neighborhood), shown below in
Figure 2. Facing Thor or its perception zone leads to the long exhaustive fight, which can lead
to defeat of Thanos. Therefore, you may assume that meeting Thor is the end of the game.
Thor’s position is unknown to you. By using the Shield of Captain America perception zones
of Thor disappear because Shield defends from hits of Stormbreaker and Mjölnir. Still, Thor
remains on its position being a serious enemy.

## Hulk

Hulk is randomly generated on the map except inside the positions of Shield, Infinity
Stone, Thor, Captain Marvel and where Thanos starts. Hulk is able to perceive Thanos by
clapping hands and understanding where produced shock waves are meeting with enemies.
Hulk’s perception includes neighbour non-diagonal cells (von Neumann neighborhood), shown
below in Figure 3. Facing Hulk or its perception zone leads to the long exhaustive fight, which
can lead to defeat of Thanos. Therefore, you may assume that meeting Hulk is the end of the
game. Hulk’s position is unknown to you. By using the Shield of Captain America perception
zones of Hulk disappear because Shield defends from shock waves. Still, Hulk remains on its
position being a serious enemy.

## Captain Marvel

Captain Marvel is randomly generated on the map except inside the positions of Shield,
Infinity Stone, Thor, Hulk, and where Thanos starts. Captain Marvel is guaranteed not to be
generated in the place, from which she immediately kills Thanos by no means to identify her.
Captain Marvel can perceive Thanos by flying up (Von Neumann neighborhood of range 2 ),
shown below in Figure 4. Facing Captain Marvel or its perception zone leads to the long
exhaustive fight, which can lead to defeat of Thanos. Therefore, you may assume that meeting
Captain Marvel is the end of the game. Captain Marvel’s position is unknown to you.

## Infinity Stone

The Infinity Stone is randomly generated on the map except inside the danger zones
(Avengers and their perception zones), Shield and where the Thanos starts. You are the only
who knows the location of the Infinity Stone.

## Shield

The lost Shield of Captain America which position is randomly generated on the map
except inside danger zones (Avengers and their perception zones), where the Infinity Stone is
located and where the Thanos starts. You don’t know the location of the Shield.

## Algorithms

- A backtracking search
- A*
You are allowed to use modified versions of algorithms, do not forget to describe modifications
in the report, if there will be any

## Variants

```
The algorithms consider two scenarios:
```
1. You can perceive the enemy and its perception zones by using his spectroscopic
    vision in Moore neighborhood cells. This can be done if you are standing next to
    their perception zone or even next to them in case of some enemies, shown below
    in Figures 5 a and 5 b. In figure 5 a, you are able to perceive 3 orange cells, in Figure
    5 b only 1. Orange cells indicate the Actor’s perception of the enemy and its zones

2. You can perceive the enemy and its perception zones using his spectroscopic vision
    in Moore neighborhood with “ears”, which extends your vision abilities in a way
    shown below in Figures 6 a and 6 b. In Figure 6 a from the Actor’s cell, you are able
    to perceive 3 orange cells. In Figure 6 b, you can perceive only 1 orange cell.

While perceiving the danger zone of enemies, Thanos can’t understand whose zone it
is. But perception of the enemy’s cell allows to identify it, also analysis of perception zones
sometimes allows to understand where is the enemy.


## Inputs and Outputs

You will be communicating with Codeforces interactor through standard inputs and
outputs (print/cout) in your code, and the interactor checks it. Your code should start by
accepting an integer (1 or 2) that represents the variant number for Thanos’ perception scenario.
Then you should accept a pair of integers representing the coordinates (𝑥,𝑦) of the Infinity
Stone. After that, you should start interacting with the interactor to explore the map. You
always start at position ( 0 , 0 ). The interactor accepts one of 2 arguments:

1. **m** _x y_ :
    - **m** is given to tell the interactor that you want to make a **m** ove
    - **_x_** and **_y_** are the coordinates you want to move to, where **_x_** stands for
       rows, and **_y_** stand for columns (value in range [0; 8])
    - The interactor will then return for you the dangers around you if there
       are any. If the cell is dangerous (contains any Avenger or his/her
       perception zone), you will receive the coordinates of this cell and the
       danger type from the interactor.
    - Note that you can only move to the allowed neighboring cells; you
       cannot teleport through the map. For example, if you are currently at
       position (3,4), you cannot send the command _m 5 6_ (will lead to to a
       failed test)
    - Output example (given you just moved to (3,4)):
```
where:
```
- First row is the number of surrounding dangers (0 if no dangers)
- Numbers in the other rows (if any) are the coordinates of the
    danger followed by the danger:
       1. **P** is **P** erception zone of enemy
       2. **H** for **H** ulk
       3. **I** for **I** nfinity **S** tone
       4. **T** for **T** hor
       5. **M** for Captain **M** arvel
       6. **S** for **S** hield
       7. And Thanos is represented with **A** on the map.
2. **e** followed by integer
- **e** represents that you reached the **e** nd
- - 1 for valid unsolvable map
- Any integer greater than 0 for the length of the shortest path to the
Infinity Stone


- Note that invalid maps won’t be provided in Codeforces tests. However,
    you are asked to think about it carefully for the report statistics

You always start in coordinates (0,0) and given your initial perception zone. When you
die, the game is over for the current test. For the next test you go back to (0,0) and start all over
again.

## Statistical Analysis

Comparison of algorithms through statistical analysis based upon 1000 test maps
generated. The statistics should provide the mean, mode median and standard deviation for
execution time, number of wins and number of loses. Also, the percentage of wins and loses
should be provided. Statistical analysis is required for both variants (described above). For each
map, comparison would be:

1. Backtracking (variant 1) compared to A* (variant 1)
2. Backtracking (variant 2) compared to A* (variant 2)
3. Backtracking (variant 1) compared to Backtracking (variant 2)
4. A* (variant 1) compared to A* (variant 2)


