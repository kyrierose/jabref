/*
Copyright (C) 2003 Nizar N. Batada, Morten O. Alver

All programs in this directory and
subdirectories are published under the GNU General Public License as
described below.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or (at
your option) any later version.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
USA

Further information about the GNU GPL is available at:
http://www.gnu.org/copyleft/gpl.ja.html

*/

package net.sf.jabref;

import java.util.Comparator;

/**
 * Compares Bibtex entries based on their 'crossref' fields. Entries including
 * this field are deemed smaller than entries without this field. This serves
 * the purpose of always placing referenced entries after referring entries in
 * the .bib file. After this criterion comes comparisons of individual fields.
 */
public class CrossRefEntryComparator implements Comparator {

    private String crossRefField = "crossref";
    private Comparator secComparator;

    public CrossRefEntryComparator(Comparator comp) {
	secComparator = comp;
    }

    public int compare(Object o1, Object o2) throws ClassCastException {
	if (!(o1 instanceof BibtexEntry) || !(o2 instanceof BibtexEntry))
	   throw new ClassCastException("Trouble comparing objects. This shouldn't happen.");
	BibtexEntry e1 = (BibtexEntry)o1,
	    e2 = (BibtexEntry)o2;

	Object f1 = e1.getField(crossRefField),
	    f2 = e2.getField(crossRefField);

	if ((f1 == null) && (f2 == null)) return secComparator.compare(e1, e2);
	if ((f1 != null) && (f2 != null)) return secComparator.compare(e1, e2);
	if ((f1 != null) && (f2 == null)) return -1;
	return 1;
    }

}
