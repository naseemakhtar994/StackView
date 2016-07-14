package com.github.rubensousa.stackview.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

public class StackDefaultAnimator extends StackAnimator {

    @Override
    public void animateReveal(Object item, View view) {
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setInterpolator(new OvershootInterpolator())
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        super.onAnimationEnd(view);
                        ViewCompat.animate(view).setListener(null);
                        getAnimationListener().onEnterFinished(view);
                    }
                });
    }

    @Override
    public void animatePop(Object item, View view) {
        ViewCompat.animate(view)
                .translationX(-view.getWidth() * 1.1f)
                .translationZ(ViewCompat.getTranslationZ(view) * 1.2f)
                .setDuration(ANIMATION_DURATION)
                .rotation(-25)
                .setInterpolator(new AccelerateDecelerateInterpolator())
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