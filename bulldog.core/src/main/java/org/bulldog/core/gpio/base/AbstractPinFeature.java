package org.bulldog.core.gpio.base;

import org.bulldog.core.gpio.Pin;
import org.bulldog.core.gpio.PinFeature;

public abstract class AbstractPinFeature implements PinFeature {

	private Pin pin;
	private boolean isSetup = false;
	
	public AbstractPinFeature(Pin pin) {
		this.pin = pin;
	}

	public Pin getPin() {
		return pin;
	}
	
	public boolean isActivatedFeature() {
		return this.getPin().getActiveFeature() == this;
	}
	
	public void activate() {
		getPin().activateFeature(getClass());
	}
	
	public void blockPin() {
		pin.block(this);
	}
	
	public boolean isBlocking() {
		return pin.getBlocker() == this;
	}
	
	public void unblockPin() {
		getPin().unblock(this);
	}
	
	protected abstract void setupImpl();
	protected abstract void teardownImpl();
	
	public void setup() {
		setupImpl();
		isSetup = true;
	}
	
	public void teardown() {
		teardownImpl();
		isSetup = false;
	}
	
	public boolean isSetup() {
		return isSetup;
	}
}
