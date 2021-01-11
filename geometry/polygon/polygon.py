import random
import constants
import math
import lab1
from point import point, PointClass
from polygon import point_in_pol
from polygon import gabarite_polygon


def create_polygon(s):
    p = []
    start = [random.randint(0, constants.SCREEN), random.randint(0, constants.SCREEN)]
    p.append(start)
    i = 1
    while i < s:
        a = [random.randint(0, constants.SCREEN), random.randint(0, constants.SCREEN)]
        p.append(a)
        i = i+1
    p.append(start)
    return p


def is_simple(p):
    for j in range(2, len(p) - 2):
        if lab1.line_segments_intersect([p[0], p[1]], [p[j], p[j + 1]]):
            return False
    for i in range(1, len(p) - 2):
        for j in range(i+2, len(p) - 1):
            if lab1.line_segments_intersect([p[i], p[i + 1]], [p[j], p[j + 1]]):
                return False
    return True


def is_convex(p):
    if not is_simple(p):
        return False
    p = p.copy()
    p.append(p[1])
    p_p = lab1.point_position([p[0], p[1]], p[2])
    for i in range(1, len(p) - 2):
        if lab1.point_position([p[i], p[i+1]], p[i+2]) != p_p:
            return False
    return True



def create_simple_polygon(s):
    p = create_polygon(s)
    while not is_simple(p):
        p = create_polygon(s)
    return p


def create_convex_polygon(s):
    p = create_polygon(s)
    while not is_convex(p):
        p = create_polygon(s)
    return p


def create_simple_in_convex(p_c, s):
    p_c.append(p_c[0])
    p_s = []
    p_start = point.create_point()
    while not point_in_pol.point_in_pol(p_c, p_start):
        p_start = point.create_point()
    p_s.append(p_start)
    p_start_2 = point.create_point()
    while not point_in_pol.point_in_pol(p_c, p_start_2):
        p_start_2 = point.create_point()
    p_s.append(p_start_2)
    i = 0
    while i < s - 2:
        p0 = point.create_point()
        if point_in_pol.point_in_pol(p_c, p0):
            p_s.append(p0)
            p_s.append(p_start)
            if not is_simple(p_s):
                p_s.pop(i + 3)
                p_s.pop(i + 2)
            else:
                p_s.pop(i + 3)
                i = i + 1
    return p_s


def angle(x, y, x1, y1):
    return (x - x1)/math.sqrt((x - x1)*(x - x1)+(y - y1)*(y - y1))



def compare(a, b):
    a1 = a[2]
    b1 = b[2]
    return a1 - b1


def min_point(p):
    p = p.copy()
    ymin = p[0][1]
    imin = 0
    for i in range(len(p)):
        if p[i][1] < ymin:
            k = True
            ymin = p[i][1]
            imin = i
    s0 = p[imin]
    return s0

def sort_points_polar(p):
    s0 = min_point(p)
    p.remove(s0)
    #sort
    for i in range(len(p)):
        p[i].append(angle(p[i][0], p[i][1], s0[0], s0[1]))
    p.sort(key=lambda x: x[2])
    for i in range(len(p)):
        del p[i][2]
    p.reverse()
    p.insert(0, s0)
    return p


def create_convex_polygon_by_grekhem(r):
    p = sort_points_polar(r)
    c_p = []
    for p0 in p:
        while len(c_p) > 1 and lab1.point_left([c_p[-2], c_p[-1]], p0):
            c_p.pop()
        c_p.append(p0)
    return c_p


def grekhem(r):
    p = sort_points_polar(r)
    c_p = []
    res = []
    c_pc = c_p.copy()
    for p0 in p:
        while len(c_p) > 1 and lab1.point_left([c_p[-2], c_p[-1]], p0):
            c_p.pop()
            c_pc = c_p.copy()
            res.append(c_pc)
        c_p.append(p0)
        c_pc = c_p.copy()
        res.append(c_pc)
    res[len(res)-1].append(p[0])
    return res

def sort_points_polar_angle(p, s0):
    assert len(p) > 0
    p.remove(s0)
    #sort
    for i in range(len(p)):
        p[i].append(angle(p[i][0], p[i][1], s0[0], s0[1]))
    p.sort(key=lambda x: x[2])
    for i in range(len(p)):
        del p[i][2]
    # p.reverse()
    #p.insert(0, s0)
    return p


def upper_arr(r, p):
    l = []
    for i in range(len(r)):
        if r[i][1] < p[1]:
            l.append(r[i])
    return l

def lower_arr(r, p):
    l = []
    for i in range(len(r)):
        if r[i][1] > p[1]:
            l.append(r[i])
    return l

def jarves(p):
    r = []

    for i in range(len(p)-1):
        r.append([])
        r[i].append(PointClass.Point.get_x(p[i]))
        r[i].append(PointClass.Point.get_y(p[i]))

    s0 = gabarite_polygon.y_max(r)
    p = sort_points_polar_angle(r, s0)
    hull = [s0, p[-1]]
    a = p[-1]
    k = len(p)
    l = sort_points_polar_angle(p, a)
    l = upper_arr(l, a)

    while k>1:
        if len(l)==0:
            break
        a = l[-1]
        hull.append(a)
        l = upper_arr(l, a)
        k = len(l)
        l.append(a)
        l = sort_points_polar_angle(l, a)
    p.append(s0)

    p = sort_points_polar_angle(r, a)
    a = p[0]
    hull.append(p[0])
    k = len(p)
    l = sort_points_polar_angle(p, a)
    l = lower_arr(l, a)
    while k>1:
        if len(l)==0:
            break
        a = l[0]
        hull.append(a)
        l = lower_arr(l, a)
        k = len(l)
        l.append(a)
        l = sort_points_polar_angle(l, a)

    return hull

    # for i in range(len(p)):




