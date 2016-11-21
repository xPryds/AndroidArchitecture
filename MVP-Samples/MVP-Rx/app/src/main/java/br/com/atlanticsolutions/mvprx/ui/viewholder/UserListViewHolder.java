package br.com.atlanticsolutions.mvprx.ui.viewholder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.atlanticsolutions.mvprx.R;
import br.com.atlanticsolutions.mvprx.application.MvpApplication;
import br.com.atlanticsolutions.mvprx.utils.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */
public class UserListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvName)
    protected TextView tvName;

    @BindView(R.id.ivAvatar)
    protected ImageView ivAvatar;

    public UserListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setName(String name) {
        this.tvName.setText(name);
    }

    public void setAvatarUrl(String avatarUrl) {
        ImageUtils.loadImageFromUrl(MvpApplication.getInstance().getApplicationContext(),avatarUrl,this.ivAvatar);
    }
    public void setAvatarDrawable(Drawable drawable) {
        this.ivAvatar.setImageDrawable(drawable);
    }
}