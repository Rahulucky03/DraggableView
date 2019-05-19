package rahul.lucky.draggable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    //private final List<User> usersList;
    private UserClickListener userClickListener;

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded = false;

    public SearchAdapter(Context context) {
        this.context = context;
        //this.usersList = new ArrayList<>();
    }

    public void setUserClickListener(UserClickListener userClickListener) {
        this.userClickListener = userClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View v1 = inflater.inflate(R.layout.single_view_item, parent, false);
                return new SearchViewHolder(v1);
            case LOADING:
                View v2 = inflater.inflate(R.layout.progress_item, parent, false);
                viewHolder = new LoadingVH(v2);
                return viewHolder;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        /*final User user = usersList.get(position);
        String name = user.getFirstName() + " " + user.getLastName();
        String location = user.getCity() + " " + user.getCountryName();
        switch (getItemViewType(position)) {
            case ITEM:
                SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
                searchViewHolder.tvUserName.setText(name);
                searchViewHolder.tvLocation.setText(location);
                Picasso.get().load(Constants.API_BASE_URL + "" + user.getProfileImage()).placeholder(R.drawable.img_placeholder).fit()
                        .into(searchViewHolder.profilePic);
                searchViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userClickListener.OnUserClick(user);
                    }
                });

                break;
            case LOADING:
//                Do nothing
                break;
        }*/
    }

    @Override
    public int getItemCount() {
        return 80;
        /*return usersList == null ? 0 : usersList.size();*/
    }

    /*@Override
    public int getItemViewType(int position) {
        return (position == usersList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(User user) {
        usersList.add(user);
        notifyItemInserted(usersList.size() - 1);
    }

    public void addAll(List<User> mcList) {
        for (User user : mcList) {
            add(user);
        }
    }

    public void remove(User user) {
        int position = usersList.indexOf(user);
        if (position > -1) {
            usersList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new User());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = usersList.size() - 1;
        User item = getItem(position);

        if (item != null) {
            usersList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public User getItem(int position) {
        return usersList.get(position);
    }*/

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profilePic;
        TextView tvUserName;
        TextView tvLocation;
        View mView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            profilePic = itemView.findViewById(R.id.post_profile_image);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            tvLocation = itemView.findViewById(R.id.tv_city_country);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userClickListener.OnUserClick();
                }
            });
        }
    }

    public class LoadingVH extends RecyclerView.ViewHolder {
        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

    public interface UserClickListener {
        void OnUserClick();
    }
}