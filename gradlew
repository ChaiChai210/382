����   30 �
 A � �
  �	  � �
  �	  �	  �	  �
 � �
 � �
  �	  �
  �
  �
  �
  �
 � �
  �
 m �
  �
  �
  �
  �	  �A�  	 � �
  �?�zxl"h

 � �
 � �	 � �	 � �
  �
  �
 m �
  �F@ ?��� � �
 * �
  �
 � �
 � � �
 � �	 0 �
 � �	 0 �
 � �
 � �
 � �
  �
 � �
 � �	 0 �
 � �	 0 �
 � �
 � �
 � � � TAG Ljava/lang/String; ConstantValue � DEBUG Z     MILLISECONDS_PER_INCH F TARGET_SEEK_SCROLL_DISTANCE_PX I  ' SNAP_TO_START���� SNAP_TO_END    SNAP_TO_ANY TARGET_SEEK_EXTRA_SCROLL_RATIO mLinearInterpolator +Landroid/view/animation/LinearInterpolator; mDecelerateInterpolator /Landroid/view/animation/DecelerateInterpolator; mTargetVector Landroid/graphics/PointF; MILLISECONDS_PER_PX mInterimTargetDx mInterimTargetDy <init> (Landroid/content/Context;)V Code LineNumberTable LocalVariableTable this 3Landroidx/recyclerview/widget/LinearSmoothScroller; context Landroid/content/Context; onStart ()V onTargetFound � State InnerClasses SmoothScroller � Action �(Landroid/view/View;Landroidx/recyclerview/widget/RecyclerView$State;Landroidx/recyclerview/widget/RecyclerView$SmoothScroller$Action;)V 
targetView Landroid/view/View; state 1Landroidx/recyclerview/widget/RecyclerView$State; action ALandroidx/recyclerview/widget/RecyclerView$SmoothScroller$Action; dx dy distance time StackMapTable � � � � onSeekTargetStep w(IILandroidx/recyclerview/widget/RecyclerView$State;Landroidx/recyclerview/widget/RecyclerView$SmoothScroller$Action;)V onStop calculateSpeedPerPixel  (Landroid/util/DisplayMetrics;)F displayMetrics Landroid/util/DisplayMetrics; calculateTimeForDeceleration (I)I calculateTimeForScrolling getHorizontalSnapPreference ()I getVerticalSnapPreference updateActionForInterimTarget D(Landroidx/recyclerview/widget/RecyclerView$SmoothScroller$Action;)V target scrollVector � clampApplyScroll (II)I tmpDt dt before calculateDtToFit (IIIII)I dtStart dtEnd 	viewStart viewEnd boxStart boxEnd snapPreference calculateDyToMakeVisible (Landroid/view/View;I)I view layoutManager � LayoutManager 9Landroidx/recyclerview/widget/RecyclerView$LayoutManager; params LayoutParams 8Landroidx/recyclerview/widget/RecyclerView$LayoutParams; top bottom start end � calculateDxToMakeVisible left right 
SourceFile LinearSmoothScroller.java 1androidx/recyclerview/widget/LinearSmoothScroller ] g )android/view/animation/LinearInterpolator T U -android/view/animation/DecelerateInterpolator V W [ L \ L � � � � � � � � Z J � � � � � � � �  � � � g � � � � X Y L � �	
 � � J J � "java/lang/IllegalArgumentException ]snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_ ] � 6androidx/recyclerview/widget/RecyclerView$LayoutParams L ! L" �# �$ � � �%&' L() L* �+ �, � 8androidx/recyclerview/widget/RecyclerView$SmoothScroller LinearSmoothScroller /androidx/recyclerview/widget/RecyclerView$State ?androidx/recyclerview/widget/RecyclerView$SmoothScroller$Action android/view/View android/graphics/PointF 7androidx/recyclerview/widget/RecyclerView$LayoutManager android/content/Context getResources !()Landroid/content/res/Resources; android/content/res/Resources getDisplayMetrics ()Landroid/util/DisplayMetrics; java/lang/Math sqrt (D)D update +(IIILandroid/view/animation/Interpolator;)V getChildCount stop android/util/DisplayMetrics 
densityDpi ceil abs x y getTargetPosition computeScrollVectorForPosition (I)Landroid/graphics/PointF; jumpTo (I)V 	normalize (Landroid/graphics/PointF;)V (Ljava/lang/String;)V getLayoutManager ;()Landroidx/recyclerview/widget/RecyclerView$LayoutManager; canScrollVertically ()Z getLayoutParams. '()Landroid/view/ViewGroup$LayoutParams; )androidx/recyclerview/widget/RecyclerView getDecoratedTop (Landroid/view/View;)I 	topMargin getDecoratedBottom bottomMargin getPaddingTop 	getHeight getPaddingBottom canScrollHorizontally getDecoratedLeft 
leftMargin getDecoratedRight rightMargin getPaddingLeft getWidth getPaddingRight/ #android/view/ViewGroup$LayoutParams android/view/ViewGroup !  A     B C  D    E  F G  D    H  I J  D      K L  D    M  N L  D    O  P L  D    Q  R L  D    H  S J  D    )  T U    V W    X Y    Z J    [ L    \ L     ] ^  _   |     4*� *� Y� � *� Y� � *� 	*� 
**+� � � � �    `       ]  Q  S  [ $ ^ 3 _ a       4 b c     4 d e   f g  _   +      �    `       g a        b c    h o  _   �     F*+*� � 6*+*� � 6hh`�� �6*� 6� -tt*� � �    `       n  o  p ( q 0 r 5 s E u a   R    F b c     F p q    F r s    F t u   ; v L   0 w L  (  x L  0  y L  z    � E  { | } ~     �  _   �     ;*� � *� �***� 	� � 	***� 
� � 
*� 	� *� 
� 	*� �    `   "      �  �  �  � & � 4 � : � a   4    ; b c     ; v L    ; w L    ; r s    ; t u  z    -  � g  _   B     **Z� 
� 	*� �    `