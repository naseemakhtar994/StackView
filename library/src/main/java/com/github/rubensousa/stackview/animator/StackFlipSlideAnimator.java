/*
 * Copyright 2016 Rúben Sousa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rubensousa.stackview.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.AccelerateInterpolator;


public class StackFlipSlideAnimator extends StackMoveAnimator {

    public StackFlipSlideAnimator(int moveDirection) {
        super(moveDirection);
    }

    @Override
    public int getAnimationDuration() {
        return 1000;
    }

    @Override
    public void animatePop(Object item, View view) {
        ViewCompat.animate(view)
                .scaleX(0.3f)
                .scaleY(0.3f)
                .setDuration(getAnimationDuration() / 10);

        if (getMoveDirection() == MOVE_LEFT || getMoveDirection() == MOVE_RIGHT) {
            ViewCompat.animate(view)
                    .translationX(getMoveDirection() == MOVE_LEFT
                            ? -view.getWidth() : view.getWidth())
                    .translationZ(ViewCompat.getTranslationZ(view) * 2.5f)
                    .rotationY(getMoveDirection() == MOVE_LEFT ? 250 : -250)
                    .setDuration(getAnimationDuration())
                    .setInterpolator(new AccelerateInterpolator())
                    .setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(View view) {
                            super.onAnimationEnd(view);
                            ViewCompat.animate(view).setListener(null);
                            getAnimationListener().onExitFinished(view);
                        }
                    });

        } else {
            ViewCompat.animate(view)
                    .translationY(getMoveDirection() == MOVE_UP
                            ? -view.getHeight() : view.getHeight())
                    .translationZ(ViewCompat.getTranslationZ(view) * 2.5f)
                    .rotationX(getMoveDirection() == MOVE_UP ? 250 : -250)
                    .setDuration(getAnimationDuration())
                    .setInterpolator(new AccelerateInterpolator())
                    .setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(View view) {
                            super.onAnimationEnd(view);
                            ViewCompat.animate(view).setListener(null);
                            getAnimationListener().onExitFinished(view);
                        }
                    });

        }
    }
}
