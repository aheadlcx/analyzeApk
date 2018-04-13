package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.app.Activity;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import c.a.d.a.a;
import cn.xiaochuan.jsbridge.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.a.d;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerVideoBean;
import cn.xiaochuankeji.tieba.json.recommend.WebPageBean;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.post.PostVoteDetailActivity;
import cn.xiaochuankeji.tieba.ui.recommend.widget.ResizeMultiDraweeView;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostVoteView;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import java.util.ArrayList;
import java.util.Iterator;

public class PostViewHolder extends BaseViewHolder {
    @BindView
    public ResizeMultiDraweeView images;
    @BindView
    public View linkContainer;
    @BindView
    public WebImageView netLinkImage;
    @BindView
    public TextView netLinkText;
    @BindView
    public View netLinkView;
    @BindView
    public TextView netsLinkAuthor;
    @BindView
    public WebImageView netsLinkImage;
    @BindView
    public TextView netsLinkTitle;
    @BindView
    public View netsLinkView;
    @BindView
    MultipleLineEllipsisTextView postContent;
    @BindView
    TextView topicName;
    @BindView
    PostVoteView voteView;
    @BindView
    public WebImageView weChatLinkImage;
    @BindView
    public View weChatLinkView;
    @BindView
    public TextView weChatLintInfo;
    @BindView
    public TextView weChatLintTitle;

    public PostViewHolder(View view, Activity activity, PostFromType postFromType, ViewGroup viewGroup) {
        super(view, activity, postFromType);
    }

    protected PostDataBean b(c cVar) {
        if (cVar == null || !(cVar instanceof PostDataBean)) {
            return null;
        }
        PostDataBean postDataBean = (PostDataBean) cVar;
        a(postDataBean);
        d(postDataBean);
        c(postDataBean);
        e(postDataBean);
        postDataBean.createTime = HolderCreator.a(this.b) ? 0 : postDataBean.createTime;
        return postDataBean;
    }

    private void a(final PostDataBean postDataBean) {
        if (TextUtils.isEmpty(postDataBean.content)) {
            this.postContent.setVisibility(8);
        } else {
            int i;
            this.postContent.setVisibility(0);
            if (postDataBean.postType == 1) {
                i = 3;
            } else {
                i = 2;
            }
            this.postContent.a(postDataBean.content, this.a, postDataBean.postId, a.a().a((int) R.color.CT_4), i);
            this.postContent.setOnExpandableTextViewListener(new MultipleLineEllipsisTextView.c(this) {
                final /* synthetic */ PostViewHolder b;

                public void onClick() {
                    this.b.a(postDataBean, "post");
                }

                public void a() {
                    this.b.a(postDataBean, false, true);
                }
            });
        }
        if (postDataBean.topic == null || TextUtils.isEmpty(postDataBean.topic.topicName)) {
            this.topicName.setVisibility(8);
            return;
        }
        OnClickListener onClickListener;
        this.topicName.setText(postDataBean.topic.topicName);
        this.topicName.setVisibility(0);
        TextView textView = this.topicName;
        if (this.b.equals(PostFromType.FROM_TOPIC)) {
            onClickListener = null;
        } else {
            onClickListener = new OnClickListener(this) {
                final /* synthetic */ PostViewHolder b;

                public void onClick(View view) {
                    this.b.a(PostDataBean.getPostFromPostDataBean(postDataBean));
                    d.a(postDataBean);
                }
            };
        }
        textView.setOnClickListener(onClickListener);
        this.topicName.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ PostViewHolder b;

            public boolean onLongClick(View view) {
                this.b.a(postDataBean, false, true);
                return true;
            }
        });
    }

    private void a(Post post) {
        TopicDetailActivity.a(this.d, post._topic, "index", post._ID);
    }

    private void d(final PostDataBean postDataBean) {
        if (postDataBean.voteInfo == null || postDataBean.voteInfo.voteItems.size() == 0) {
            this.voteView.setVisibility(8);
            return;
        }
        this.voteView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ PostViewHolder b;

            public boolean onLongClick(View view) {
                this.b.a(postDataBean, false, true);
                return true;
            }
        });
        this.voteView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PostViewHolder b;

            public void onClick(View view) {
                this.b.b(postDataBean.postId, postDataBean.voteInfo.id);
                d.a(postDataBean);
            }
        });
        this.voteView.a(postDataBean.voteInfo, postDataBean.postId, "topic", this.postContent.getWidth());
        this.voteView.setVisibility(0);
    }

    public void c(final PostDataBean postDataBean) {
        if (postDataBean.webPage == null) {
            this.linkContainer.setVisibility(8);
            return;
        }
        final WebPageBean webPageBean = postDataBean.webPage;
        this.linkContainer.setVisibility(0);
        this.netLinkView.setVisibility(8);
        this.weChatLinkView.setVisibility(8);
        this.netsLinkView.setVisibility(8);
        switch (webPageBean.type) {
            case 0:
                this.netLinkView.setVisibility(0);
                this.netLinkImage.setImageResource(c.a.c.e().c() ? R.drawable.image_link_placeholder_night : R.drawable.image_link_placeholder);
                this.netLinkImage.setImageURI(webPageBean.thumbUrl);
                this.netLinkText.setText(TextUtils.isEmpty(webPageBean.title) ? webPageBean.url : webPageBean.title);
                this.netLinkView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostViewHolder c;

                    public void onClick(View view) {
                        this.c.a(webPageBean.url);
                        d.a(postDataBean);
                    }
                });
                this.netLinkView.setOnLongClickListener(new OnLongClickListener(this) {
                    final /* synthetic */ PostViewHolder b;

                    public boolean onLongClick(View view) {
                        this.b.a(postDataBean, false, true);
                        return true;
                    }
                });
                return;
            case 1:
                this.weChatLinkView.setVisibility(0);
                this.weChatLinkImage.setImageURI(webPageBean.thumbUrl);
                this.weChatLintTitle.setText(webPageBean.title);
                this.weChatLintInfo.setText(webPageBean.describe);
                this.weChatLinkView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostViewHolder b;

                    public void onClick(View view) {
                        this.b.a(postDataBean, "post");
                    }
                });
                this.weChatLinkView.setOnLongClickListener(new OnLongClickListener(this) {
                    final /* synthetic */ PostViewHolder b;

                    public boolean onLongClick(View view) {
                        this.b.a(postDataBean, false, true);
                        return true;
                    }
                });
                return;
            case 3:
                this.netsLinkView.setVisibility(0);
                this.netsLinkAuthor.setText(webPageBean.author);
                this.netsLinkImage.setImageURI(webPageBean.thumbUrl);
                this.netsLinkTitle.setText(webPageBean.title);
                this.netsLinkView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ PostViewHolder b;

                    public void onClick(View view) {
                        this.b.a(postDataBean, "post");
                    }
                });
                this.netsLinkView.setOnLongClickListener(new OnLongClickListener(this) {
                    final /* synthetic */ PostViewHolder b;

                    public boolean onLongClick(View view) {
                        this.b.a(postDataBean, false, true);
                        return true;
                    }
                });
                return;
            default:
                return;
        }
    }

    private void e(final PostDataBean postDataBean) {
        if (postDataBean.images == null || postDataBean.images.isEmpty()) {
            this.images.setVisibility(8);
            return;
        }
        if (postDataBean.videoJsons != null && postDataBean.videoJsons.size() > 0) {
            for (ServerImageBean serverImageBean : postDataBean.images) {
                serverImageBean.videoBean = (ServerVideoBean) postDataBean.videoJsons.get(String.valueOf(serverImageBean.id));
            }
        }
        this.images.setVisibility(0);
        this.images.setImageUris(postDataBean.images);
        this.images.setOnItemClickListener(new ResizeMultiDraweeView.a(this) {
            final /* synthetic */ PostViewHolder b;

            public void a(int i, Rect rect) {
                this.b.a(i, postDataBean);
                d.a(postDataBean);
            }

            public void a() {
                this.b.a(postDataBean, false, true);
            }
        });
    }

    protected void a(int i, PostDataBean postDataBean) {
        ArrayList arrayList = new ArrayList(postDataBean.images.size());
        ArrayList arrayList2 = new ArrayList(postDataBean.images.size());
        Post postFromPostDataBean = PostDataBean.getPostFromPostDataBean(postDataBean);
        Iterator it = postFromPostDataBean._imgList.iterator();
        while (it.hasNext()) {
            cn.htjyb.b.a a;
            ServerImage serverImage = (ServerImage) it.next();
            arrayList.add(cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPic228, serverImage.postImageId));
            if (serverImage.isVideo()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(postFromPostDataBean.getImgVideoBy(serverImage.postImageId).getUrl(), Type.kVideo, serverImage.postImageId);
            } else if (serverImage.isMP4()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kMP4, (long) serverImage.mp4Id);
            } else if (serverImage.isGif()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kGif, serverImage.postImageId);
            } else {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPicLarge, serverImage.postImageId);
            }
            a.setRotate(serverImage.rotate);
            arrayList2.add(a);
        }
        MediaBrowseActivity.a(this.d, i, postFromPostDataBean, arrayList, arrayList2, postFromPostDataBean._imgList, postFromPostDataBean.imgVideos, EntranceType.PostDetail);
    }

    private void b(long j, long j2) {
        PostVoteDetailActivity.a(this.itemView.getContext(), j, j2, 0);
    }

    public void a(String str) {
        try {
            if (!Uri.parse(str).isHierarchical()) {
                g.a("不是一个有效的url");
            }
        } catch (Exception e) {
            g.a("不是一个有效的url");
        }
        WebActivity.a(this.d, b.a(null, str));
    }
}
