package jp.wasabeef.recyclerview.animators;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

	@Override
	protected void animateMoveImpl(final RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
		final View view = holder.itemView;
		final int deltaX = toX - fromX;
		final int deltaY = toY - fromY;
		if(deltaX != 0) {
			ViewCompat.animate(view).translationX(0);
		}
		if(deltaY != 0) {
			ViewCompat.animate(view).translationY(0);
		}
		// TODO: make EndActions end listeners instead, since end actions aren't called when
		// vpas are canceled (and can't end them. why?)
		// need listener functionality in VPACompat for this. Ick.
		mMoveAnimations.add(holder);
		final ViewPropertyAnimatorCompat animation = ViewCompat.animate(view);
		animation.setStartDelay(mStepDelay * 2).setDuration(getMoveDuration()).setListener(new VpaListenerAdapter() {
			@Override
			public void onAnimationStart(View view) {
				dispatchMoveStarting(holder);
			}

			@Override
			public void onAnimationCancel(View view) {
				if(deltaX != 0) {
					ViewCompat.setTranslationX(view, 0);
				}
				if(deltaY != 0) {
					ViewCompat.setTranslationY(view, 0);
				}
			}

			@Override
			public void onAnimationEnd(View view) {
				animation.setListener(null);
				dispatchMoveFinished(holder);
				mMoveAnimations.remove(holder);
				dispatchFinishedWhenDone();
			}
		}).start();
	}

	protected abstract void animateAddImpl(final RecyclerView.ViewHolder holder, int delay);

	@Override
	protected void tryAnimateAdd(final ArrayList<RecyclerView.ViewHolder> additions) {
		int delay = mStepDelay;
		for(RecyclerView.ViewHolder holder : additions) {
			animateAddImpl(holder, delay);
			delay += mStepDelay;
		}
		additions.clear();
		mAdditionsList.remove(additions);
	}

}
