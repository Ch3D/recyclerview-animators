package jp.wasabeef.recyclerview.animators;

/**
 * Copyright (C) 2015 Ch3D
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;

public class FlipInTopXDelayedAnimator extends BaseItemAnimatorDelayed {

	public static final float SCALE_START = 0.75f;

	public static final float SCALE_FINISH = 1.0f;

	public static final int ROTATION_START = 90;

	public static final int ROTATION_FINISH = 0;

	public static final float ALPHA_START = 0.5f;

	public static final float ALPHA_FINISH = 1.0f;

	public static final int TRANSLATION_START = -48;

	public static final int TRANSLATION_FINISH = 0;

	public FlipInTopXDelayedAnimator(final int duration, final int stepDelay) {
		super(duration, stepDelay);
	}

	@Override
	protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
		ViewCompat.animate(holder.itemView)
		          .rotationX(ROTATION_START)
		          .scaleX(SCALE_START)
		          .scaleY(SCALE_START)
		          .alpha(ALPHA_START)
		          .translationY(TRANSLATION_START)
		          .setDuration(getRemoveDuration())
		          .setListener(new DefaultRemoveVpaListener(holder))
		          .start();
		mRemoveAnimations.add(holder);
	}

	@Override
	protected void preAnimateAdd(RecyclerView.ViewHolder holder) {
		ViewCompat.setRotationX(holder.itemView, ROTATION_START);
		ViewCompat.setAlpha(holder.itemView, ALPHA_START);
		ViewCompat.setScaleX(holder.itemView, SCALE_START);
		ViewCompat.setScaleY(holder.itemView, SCALE_START);
		ViewCompat.setTranslationY(holder.itemView, TRANSLATION_START);
	}

	@Override
	protected void animateAddImpl(final RecyclerView.ViewHolder holder, int delay) {
		ViewCompat.animate(holder.itemView)
		          .rotationX(ROTATION_FINISH)
		          .setStartDelay(delay)
		          .alpha(ALPHA_FINISH)
		          .scaleX(SCALE_FINISH)
		          .scaleY(SCALE_FINISH)
		          .translationY(TRANSLATION_FINISH)
		          .setDuration(getAddDuration())
		          .setListener(new DefaultAddVpaListener(holder)).start();
		mAddAnimations.add(holder);
	}
}
