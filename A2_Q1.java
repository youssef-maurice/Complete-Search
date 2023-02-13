import java.util.HashMap;

public class A2_Q1 {

    //function to get the number of balls in a board
    public static int count_balls(String[][] board){
        int rows= board.length;
        int cols= board[0].length;
        //initiate counter at 0
        int balls= 0;
        //iterate through all the spots on the board
        for (int row=0; row<rows; row++) {
            for (int col = 0; col < cols; col++) {
                //if the spot contains a ball
                if(board[row][col].equals("o")){
                    //increase counter
                    balls++;
                }
            }
        }
        //return the number of balls on the board
        return balls;
    }
    //function to jump right
    public static void right(String board[][], int row, int col) {
        //put ball in spot 2 columns to the right
        board[row][col+2]="o";
        //delete the chosen spot
        board[row][col]=".";
        //delete the spot we are jumping over
        board[row][col+1]=".";
    }
    //function to undo the jump right
    public static void undo_right(String board[][], int row, int col) {
        //delete ball from the spot we jumped to
        board[row][col+2]=".";
        //put ball in original spot
        board[row][col]="o";
        //put ball in the spot we jumped over
        board[row][col+1]="o";
    }
    //function to jump left
    public static void left(String board[][], int row, int col) {
        //put ball in spot 2 columns to the left
        board[row][col-2]="o";
        //delete the chosen spot
        board[row][col]=".";
        //delete the spot we are jumping over
        board[row][col-1]=".";
    }
    //function to undo the jump left
    public static void undo_left(String board[][], int row, int col) {
        //delete ball from the spot we jumped to
        board[row][col-2]=".";
        //put ball in original spot
        board[row][col]="o";
        //put ball in the spot we jumped over
        board[row][col-1]="o";
    }
    //function to jump up
    public static void up(String board[][], int row, int col) {
        //put ball in spot 2 columns up
        board[row-2][col]="o";
        //delete the chosen spot
        board[row][col]=".";
        //delete the spot we are jumping over
        board[row-1][col]=".";
    }
    //function to undo the jump up
    public static void undo_up(String board[][], int row, int col) {
        //delete ball from the spot we jumped to
        board[row-2][col]=".";
        //put ball in original spot
        board[row][col]="o";
        //put ball in the spot we jumped over
        board[row-1][col]="o";
    }
    //function to jump down
    public static void down(String board[][], int row, int col) {
        //put ball in spot 2 columns down
        board[row+2][col]="o";
        //delete the chosen spot
        board[row][col]=".";
        //delete the spot we are jumping over
        board[row+1][col]=".";
    }
    //function to undo the jump down
    public static void undo_down(String board[][], int row, int col) {
        //delete ball from the spot we jumped to
        board[row+2][col]=".";
        //put ball in original spot
        board[row][col]="o";
        //put ball in the spot we jumped over
        board[row+1][col]="o";
    }

    //helper function that takes a board, balls on the board, number of jumps
    //needed to arrive to the board and a hashmap that maps the number of balls
    //to smallest number of jumps posssible
    public static HashMap<Integer, Integer> backtrack(String[][] board, int jumps, HashMap<Integer, Integer> map){
        int rows= board.length;
        int cols= board[0].length;
        int balls= count_balls(board);
        //iterate through all the spots on the board
        for (int row=0; row<rows; row++) {
            for (int col = 0; col < cols; col++) {
                //RIGHT
                //if spot stores a ball and there exists 2 more columns to the right
                if(board[row][col].equals("o") && col+2<cols){
                    //if the spot on the right stores a ball and the one after is empty
                    if(board[row][col+1].equals("o") && board[row][col+2].equals(".")){
                        //execute a jump to the right for the given ball
                        right(board, row, col);
                        //increase number of jumps
                        jumps++;
                        //call the function recursively on the new board
                        backtrack(board, jumps, map);
                        //undo the jump to the right
                        undo_right(board, row, col);
                        //decrease number of jumps
                        jumps--;
                    }
                }
                //LEFT
                //if spot stores a ball and there exists 2 more columns to the left
                if(board[row][col].equals("o") && col-2>=0){
                    //if the spot on the left stores a ball and the one after is empty
                    if(board[row][col-1].equals("o") && board[row][col-2].equals(".")){
                        //execute a jump to the left for the given ball
                        left(board, row, col);
                        //increase number of jumps
                        jumps++;
                        //call the function recursively on the new board
                        backtrack(board, jumps, map);
                        //undo the jump to the left
                        undo_left(board, row, col);
                        //decrease number of jumps
                        jumps--;
                    }
                }
                //UP
                //if spot stores a ball and there exists 2 more rows up
                if(board[row][col].equals("o") && row-2>=0){
                    //if the spot up one row stores a ball and the one after is empty
                    if(board[row-1][col].equals("o") && board[row-2][col].equals(".")){
                        //execute a jump up for the given ball
                        up(board, row, col);
                        //increase number of jumps
                        jumps++;
                        //call the function recursively on the new board
                        backtrack(board, jumps, map);
                        //undo the jump up
                        undo_up(board, row, col);
                        //decrease number of jumps
                        jumps--;
                    }
                }
                //DOWN
                //if spot stores a ball and there exists 2 more rows down
                if(board[row][col].equals("o") && row+2<rows){
                    //if the spot down one row stores a ball and the one after is empty
                    if(board[row+1][col].equals("o") && board[row+2][col].equals(".")){
                        //execute a jump down for the given ball
                        down(board, row, col);
                        //increase number of jumps
                        jumps++;
                        //call the function recursively on the new board
                        backtrack(board, jumps, map);
                        //undo the jump down
                        undo_down(board, row, col);
                        //decrease number of jumps
                        jumps--;
                    }
                }
            }
        }
        //if the hashmap already contains the number of balls as key
        if(map.containsKey(balls)){
            //if the number of jumps stored is bigger than the number of jumps calculated
            if(map.get(balls)>jumps){
                //replace old number of jumps with the smaller amd newer number of jumps
                map.put(balls, jumps);
            }
            //if the hashmap does not contain the number of balls as key
        }else{
            //map the number of balls to the number of jumps and add them to
            //hashmap
            map.put(balls, jumps);
        }
        //return the hashmap that maps from number of balls to the
        //smallest number of jumps needed
        return map;
    }

    public static int[] game(String[][] board){
        int max_balls = board[0].length * board.length;
        int balls = count_balls(board);
        int jumps= 0;
        HashMap<Integer, Integer> balls_count = new HashMap<Integer, Integer>();
        //if the board is empty
        if(balls==0){
            int[] arr= {0,0};
            return arr;
        }
        //get hashmap that maps the number of balls to smallest number of jumps posssible
        HashMap<Integer, Integer> map = backtrack(board, jumps, balls_count);
        //initialize the key to the maximum number of balls possible
        int key = max_balls;
        //iterate through all the keys in the hashmap
        for(int num: map.keySet()){
            //if key is samller than the variable storing the smallest key
            if(num<key){
                //replace smallest key with the key
                key= num;
            }
        }
        //create array where the first element is the smallest number
        //of balls. The second element is the number of jumps mapped to by that
        //key in the hashmap
        int[] res= {key, map.get(key)};
        //return the array
        return res;
    }
}
