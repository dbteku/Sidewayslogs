package settings;

import interfaces.Setting;

public class VerticalLock implements Setting{
	
	private final String VERTICAL_NAME = "Vertical-Lock";
	private boolean verticalLock;
	
	public VerticalLock(boolean isEnabled) {
		this.verticalLock = isEnabled;
	}

	@Override
	public boolean isEnabled() {
		return verticalLock;
	}

	@Override
	public String getName() {
		return VERTICAL_NAME;
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		this.verticalLock = isEnabled;
	}

}
