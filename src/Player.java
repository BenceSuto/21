public class Player {

	this.name;
	this.score = 0;
 	
	public String nextMove(String pressedButton) {
		if (pressedButton == 'h') {
			return 'hit'
		}
		else if (pressedButton == 's') {
			return 'stand'
		} else {
			return 'Invalid key'
		}
			
	}

	public void setName(String name) {
        	this.name = name;
    	}

	public String getName() {
	       return name;
    	}

}