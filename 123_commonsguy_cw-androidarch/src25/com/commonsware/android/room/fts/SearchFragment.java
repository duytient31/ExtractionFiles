/***
 Copyright (c) 2017-2018 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain	a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed search an "AS IS" BASIS,	WITHOUT	WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _Android's Architecture Components_
 https://commonsware.com/AndroidArch
 */

package com.commonsware.android.room.fts;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedListAdapter;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SearchFragment extends RecyclerViewFragment {
  private static final String ARG_EXPR="expr";

  static SearchFragment newInstance(String expr) {
    SearchFragment result=new SearchFragment();
    Bundle args=new Bundle();

    args.putString(ARG_EXPR, expr);
    result.setArguments(args);

    return result;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setLayoutManager(new LinearLayoutManager(getActivity()));
    getRecyclerView()
      .addItemDecoration(new DividerItemDecoration(getActivity(),
        LinearLayoutManager.VERTICAL));

    SearchViewModel vm=ViewModelProviders.of(this).get(SearchViewModel.class);
    BookSearchAdapter adapter=
      new BookSearchAdapter(getActivity().getLayoutInflater());

    vm.search(getArguments().getString(ARG_EXPR))
      .observe(this, adapter::submitList);

    setAdapter(adapter);
  }

  private static class BookSearchAdapter extends PagedListAdapter<BookSearchResult, RowHolder> {
    private final LayoutInflater inflater;

    BookSearchAdapter(LayoutInflater inflater) {
      super(SEARCH_DIFF);
      this.inflater=inflater;
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return(new RowHolder(inflater.inflate(R.layout.row, parent, false)));
    }

    @Override
    public void onBindViewHolder(RowHolder holder, int position) {
      BookSearchResult result=getItem(position);

      if (result==null) {
        holder.clear();
      }
      else {
        holder.bind(result);
      }
    }
  }

  private static class RowHolder extends RecyclerView.ViewHolder {
    private final TextView prose;

    RowHolder(View itemView) {
      super(itemView);

      prose=itemView.findViewById(R.id.prose);
    }

    void bind(BookSearchResult result) {
      prose.setText(Html.fromHtml(result.snippet));
    }

    void clear() {
      prose.setText(null);
    }
  }

  static final DiffUtil.ItemCallback<BookSearchResult> SEARCH_DIFF=
    new DiffUtil.ItemCallback<BookSearchResult>() {
      @Override
      public boolean areItemsTheSame(BookSearchResult oldItem,
                                     BookSearchResult newItem) {
        return oldItem==newItem;
      }

      @Override
      public boolean areContentsTheSame(BookSearchResult oldItem,
                                        BookSearchResult newItem) {
        return oldItem.snippet.equals(newItem.snippet);
      }
  };
}
