package datas;

public class Checker implements LearningfinishedCallback {
	
	private boolean finished = false;
	@Override
	public void informLearningStatus(boolean status) {
		
		this.finished = status;
	}
	@Override
	public boolean getLearningStatus() {
		// TODO Auto-generated method stub
		return finished;
	}

	
	
}
