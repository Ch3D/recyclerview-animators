package jp.wasabeef.recyclerview.animators;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Ch3D on 05.03.2015.
 */
public abstract class BaseItemAnimatorDelayed extends BaseItemAnimator {

	protected final int mStepDelay;

	public BaseItemAnimatorDelayed(final int duration, final int stepDelay) {
		mStepDelay = stepDelay;
		setRemoveDuration(duration);
		setAddDuration(duration);
		setChangeDuration(duration);
		setMoveDuration(duration);
	}

	protected abstract void animateAddImpl(final RecyclerView.ViewHolder holder, int delay);

	@Override
	protected void tryAnimateAdd(final ArrayList<RecyclerView.ViewHolder> additions) {
		int delay = mStepDelay;
		for(RecyclerView.ViewHolder holder : additions) {
			animateAddImpl(holder, delay);
			delay += 50;
		}
		additions.clear();
		mAdditionsList.remove(additions);
	}
}
