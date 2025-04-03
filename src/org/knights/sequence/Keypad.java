package org.knights.sequence;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * Keypad
 *
 * Created by BrowneA on 8/2/2016.
 *
 */
class Keypad {
    final private static Key A = new Key('A', Boolean.TRUE);
    final private static Key B = new Key('B', Boolean.FALSE);
    final private static Key C = new Key('C', Boolean.FALSE);
    final private static Key D = new Key('D', Boolean.FALSE);
    final private static Key E = new Key('E', Boolean.TRUE);
    final private static Key F = new Key('F', Boolean.FALSE);
    final private static Key G = new Key('G', Boolean.FALSE);
    final private static Key H = new Key('H', Boolean.FALSE);
    final private static Key I = new Key('I', Boolean.TRUE);
    final private static Key J = new Key('J', Boolean.FALSE);
    final private static Key K = new Key('K', Boolean.FALSE);
    final private static Key L = new Key('L', Boolean.FALSE);
    final private static Key M = new Key('M', Boolean.FALSE);
    final private static Key N = new Key('N', Boolean.FALSE);
    final private static Key O = new Key('O', Boolean.TRUE);
    final private static Key _1 = new Key('1', Boolean.FALSE);
    final private static Key _2 = new Key('2', Boolean.FALSE);
    final private static Key _3 = new Key('3', Boolean.FALSE);

    ArrayList<Key> getKeysArrayList() {
        ArrayList<Key> keys = new ArrayList<Key>();
        keys.add(A.setValidMovesArrayList(new Key[] {H, L}));
        keys.add(B.setValidMovesArrayList(new Key[] {I, K, M}));
        keys.add(C.setValidMovesArrayList(new Key[] {F, J, L, N}));
        keys.add(D.setValidMovesArrayList(new Key[] {G, M, O}));
        keys.add(E.setValidMovesArrayList(new Key[] {H, N}));
        keys.add(F.setValidMovesArrayList(new Key[] {C, M, _1}));
        keys.add(G.setValidMovesArrayList(new Key[] {D, N, _2}));
        keys.add(H.setValidMovesArrayList(new Key[] {A, E, K, O, _1, _3}));
        keys.add(I.setValidMovesArrayList(new Key[] {B, L, _2}));
        keys.add(J.setValidMovesArrayList(new Key[] {C, M, _3}));
        keys.add(K.setValidMovesArrayList(new Key[] {B, H, _2}));
        keys.add(L.setValidMovesArrayList(new Key[] {A, C, I, _3}));
        keys.add(M.setValidMovesArrayList(new Key[] {B, D, F, J}));
        keys.add(N.setValidMovesArrayList(new Key[] {C, E, G, _1}));
        keys.add(O.setValidMovesArrayList(new Key[] {D, H, _2}));
        keys.add(_1.setValidMovesArrayList(new Key[] {F, H, N}));
        keys.add(_2.setValidMovesArrayList(new Key[] {G, I, K, O}));
        keys.add(_3.setValidMovesArrayList(new Key[] {H, J, L}));
        return keys;
    }

    Set <Key> getKeysHashSet() {
        Set <Key> keys = new LinkedHashSet<Key>();
        keys.add(A.setValidMovesHashSet(new Key[] {H, L}));
        keys.add(B.setValidMovesHashSet(new Key[] {I, K, M}));
        keys.add(C.setValidMovesHashSet(new Key[] {F, J, L, N}));
        keys.add(D.setValidMovesHashSet(new Key[] {G, M, O}));
        keys.add(E.setValidMovesHashSet(new Key[] {H, N}));
        keys.add(F.setValidMovesHashSet(new Key[] {C, M, _1}));
        keys.add(G.setValidMovesHashSet(new Key[] {D, N, _2}));
        keys.add(H.setValidMovesHashSet(new Key[] {A, E, K, O, _1, _3}));
        keys.add(I.setValidMovesHashSet(new Key[] {B, L, _2}));
        keys.add(J.setValidMovesHashSet(new Key[] {C, M, _3}));
        keys.add(K.setValidMovesHashSet(new Key[] {B, H, _2}));
        keys.add(L.setValidMovesHashSet(new Key[] {A, C, I, _3}));
        keys.add(M.setValidMovesHashSet(new Key[] {B, D, F, J}));
        keys.add(N.setValidMovesHashSet(new Key[] {C, E, G, _1}));
        keys.add(O.setValidMovesHashSet(new Key[] {D, H, _2}));
        keys.add(_1.setValidMovesHashSet(new Key[] {F, H, N}));
        keys.add(_2.setValidMovesHashSet(new Key[] {G, I, K, O}));
        keys.add(_3.setValidMovesHashSet(new Key[] {H, J, L}));
        return keys;
    }
}

