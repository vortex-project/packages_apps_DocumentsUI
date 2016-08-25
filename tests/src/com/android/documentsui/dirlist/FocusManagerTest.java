/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.documentsui.dirlist;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.documentsui.testing.TestRecyclerView;
import com.android.documentsui.testing.dirlist.SelectionProbe;
import com.android.documentsui.testing.dirlist.TestSelectionListener;

import java.util.List;

@SmallTest
public class FocusManagerTest extends AndroidTestCase {

    private static final String TEST_AUTHORITY = "test_authority";

    private static final List<String> ITEMS = TestData.create(10);

    private FocusManager mManager;
    private TestRecyclerView mView;

    @Override
    public void setUp() throws Exception {
        mView = TestRecyclerView.create(ITEMS);
        mManager = new FocusManager(mView, new TestModel(TEST_AUTHORITY), 0);
    }

    public void testFocus() {
        mManager.onDirectoryCreated(Integer.toString(3));
        mView.assertItemViewFocused(3);
     }

    public void testPendingFocus() {
       mManager.onDirectoryCreated(Integer.toString(10));
       List<String> mutableItems = TestData.create(11);
       mView.setItems(mutableItems);
       mManager.onLayoutCompleted();
       // Should only be called once
       mView.assertItemViewFocused(10);
    }
}
