package jp.wasabeef.recyclerview.animators;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Ch3D on 05.03.2015.
 */
public abstract class BaseItemAnimatorSimple extends BaseItemAnimator {

	protected abstract void animateAddImpl(final RecyclerView.ViewHolder holder);

	@Override
	protected void tryAnimateAdd(final ArrayList<RecyclerView.ViewHolder> additions) {
		for(RecyclerView.ViewHolder holder : additions) {
			animateAddImpl(holder);
		}
		additions.clear();
		mAdditionsList.remove(additions);
	}
}
