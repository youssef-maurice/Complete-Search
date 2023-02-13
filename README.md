# Complete-Search

Provided is a two-dimensional board with some holes on it, where
each hole can contain a small ball. During the game, and in each turn, you will be allowed to
jump (horizontally or vertically) over an adjacent ball into the empty hole next to the jumped ball
in line with it. Once the jump is performed, the ball (which was jumped over) is then removed
from the board. The idea of the game is to apply the minimum number of moves (i.e., jumps) to
end in a state with a minimum number of balls.

The task is to develop an algorithm (i.e., a complete search one) that determines the minimum 
number of balls that remains in the board after applying (recursively)
the above-mentioned movement. You will also need to report the minimum number of moves
required to reach that number of balls.
