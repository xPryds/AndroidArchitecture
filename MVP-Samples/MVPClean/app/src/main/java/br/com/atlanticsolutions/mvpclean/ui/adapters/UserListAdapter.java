package br.com.atlanticsolutions.mvpclean.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.atlanticsolutions.mvpclean.R;
import br.com.atlanticsolutions.mvpclean.logic.model.pojo.User;
import br.com.atlanticsolutions.mvpclean.ui.viewholder.UserListViewHolder;

/**
 * Created by Alessandro Valenza on 31/10/2016.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    private final Context context;
    private List<User> items;

    public UserListAdapter(Context context, List<User> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_user_list_item, parent, false);
        return new UserListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserListViewHolder holder, int position) {
        User item = items.get(position);

        StringBuilder builder = new StringBuilder();
        builder.append(item.getFirstName())
                .append(" ")
                .append(item.getLastName());

        holder.setName(builder.toString());
        holder.setAvatarUrl(item.getAvatar());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public void add(User object) {
        if (object == null)
            object = new User();

        items.add(object);
        int pos = items.indexOf(object);
        notifyItemInserted(pos);
    }

    public void remove(int pos) {
        if (pos > items.size() - 1)
            return;

        items.remove(pos);
        notifyItemRemoved(pos);
    }

    public void remove(User object) {
        if (object == null)
            return;

        int pos = items.indexOf(object);
        items.remove(pos);
        notifyItemRemoved(pos);
    }

    public void replaceAll(ArrayList<User> users) {
        items.clear();
        items.addAll(users);
        notifyDataSetChanged();
    }
}