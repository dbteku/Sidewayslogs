package com.dbteku.models;

import com.dbteku.interfaces.Setting;

public class VerticalLockSetting implements Setting{
	
	private final String playerId;
	private boolean verticalLock;
	
	public VerticalLockSetting(String playerId, boolean isEnabled) {
		this.playerId = playerId;
		this.verticalLock = isEnabled;
	}

	public String getPlayerId() {
		return playerId;
	}
	
	@Override
	public boolean isEnabled() {
		return verticalLock;
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		this.verticalLock = isEnabled;
	}

}
