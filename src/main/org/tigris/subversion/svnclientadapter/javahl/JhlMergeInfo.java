/*******************************************************************************
 * Copyright (c) 2007 svnClientAdapter project and others.
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
 *
 * Contributors:
 *     svnClientAdapter project committers - initial API and implementation
 ******************************************************************************/
package org.tigris.subversion.svnclientadapter.javahl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.tigris.subversion.javahl.MergeInfo;
import org.tigris.subversion.javahl.RevisionRange;
import org.tigris.subversion.javahl.SubversionException;
import org.tigris.subversion.svnclientadapter.ISVNMergeInfo;
import org.tigris.subversion.svnclientadapter.SVNRevisionRange;

public class JhlMergeInfo implements ISVNMergeInfo {
	
	MergeInfo info;
	
	public JhlMergeInfo() {
		super();
		info = new MergeInfo();
	}

	public JhlMergeInfo(MergeInfo info) {
		super();
		this.info = info;
	}

	public void addRevisionRange(String path, SVNRevisionRange range) {
		info.addRevisionRange(path, JhlConverter.convert(range));
	}

	public void addRevisions(String path, SVNRevisionRange[] range) {
		List rangeList = new ArrayList();
		for (int i = 0; i < range.length; i++) {
			rangeList.add(JhlConverter.convert(range[i]));
		}
		try {
			info.addRevisions(path, rangeList);
		} catch (SubversionException e) {
			// Will never happen because we know all objects
			// are the right type
		}
	}

	public String[] getPaths() {
		return info.getPaths();
		
	}

	public SVNRevisionRange[] getRevisionRange(String path) {
		return JhlConverter.convert(info.getRevisionRange(path));
	}

	public SVNRevisionRange[] getRevisions(String path) {
		List rangeList = info.getRevisions(path);
		SVNRevisionRange[] range = new SVNRevisionRange[rangeList.size()];
		int i = 0;
		for (Iterator iter = rangeList.iterator(); iter.hasNext();) {
			range[i] = JhlConverter.convert((RevisionRange) iter.next());
		}
		return range;
	}

	public void loadFromMergeInfoProperty(String mergeInfo) {
		info.loadFromMergeInfoProperty(mergeInfo);
	}

}