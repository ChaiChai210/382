����   3 � 3com/chad/library/adapter/base/MultipleItemRvAdapter  ~<T:Ljava/lang/Object;V:Lcom/chad/library/adapter/base/BaseViewHolder;>Lcom/chad/library/adapter/base/BaseQuickAdapter<TT;TV;>; .com/chad/library/adapter/base/BaseQuickAdapter  MultipleItemRvAdapter.java 5com/chad/library/adapter/base/MultipleItemRvAdapter$3  5com/chad/library/adapter/base/MultipleItemRvAdapter$2 	 5com/chad/library/adapter/base/MultipleItemRvAdapter$1  Bcom/chad/library/adapter/base/BaseQuickAdapter$OnItemClickListener  OnItemClickListener Fcom/chad/library/adapter/base/BaseQuickAdapter$OnItemLongClickListener  OnItemLongClickListener !android/view/View$OnClickListener  android/view/View  OnClickListener %android/view/View$OnLongClickListener  OnLongClickListener mItemProviders Landroid/util/SparseArray; ULandroid/util/SparseArray<Lcom/chad/library/adapter/base/provider/BaseItemProvider;>; mProviderDelegate 5Lcom/chad/library/adapter/base/util/ProviderDelegate; <init> (Ljava/util/List;)V Landroidx/annotation/Nullable;   !
  # this =Lcom/chad/library/adapter/base/MultipleItemRvAdapter<TT;TV;>; 5Lcom/chad/library/adapter/base/MultipleItemRvAdapter; data Ljava/util/List<TT;>; Ljava/util/List; finishInitialize ()V 3com/chad/library/adapter/base/util/ProviderDelegate -   ,
 . /  	  1 8(Lcom/chad/library/adapter/base/MultipleItemRvAdapter;)V   3
  4 setMultiTypeDelegate 9(Lcom/chad/library/adapter/base/util/MultiTypeDelegate;)V 6 7
  8 registerItemProvider : ,
  ; getItemProviders ()Landroid/util/SparseArray; = >
 . ?  	  A android/util/SparseArray C size ()I E F
 D G keyAt (I)I I J
 D K get (I)Ljava/lang/Object; M N
 D O 7com/chad/library/adapter/base/provider/BaseItemProvider Q mData S *	  T	 R T getMultiTypeDelegate 8()Lcom/chad/library/adapter/base/util/MultiTypeDelegate; W X
  Y layout [ F
 R \ 4com/chad/library/adapter/base/util/MultiTypeDelegate ^ registerItemType :(II)Lcom/chad/library/adapter/base/util/MultiTypeDelegate; ` a
 _ b key I provider 9Lcom/chad/library/adapter/base/provider/BaseItemProvider; i getViewType (Ljava/lang/Object;)I convert C(Lcom/chad/library/adapter/base/BaseView