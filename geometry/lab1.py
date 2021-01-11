import math


def point_left(l, p0):
    d = det(l[1], l[0], p0)
    if d > 0:
        return True
    if d < 0:
        return False


def point_on_line(l, p2):
    d = det(l[0], l[1], p2)
    if d == 0:
        return True
    return False


def point_position(l, p):
    d = det(l[1], l[0], p)
    if d > 0:
        return 1
    if d < 0:
        return -1
    return 0


def line_segments_intersect(l1, l2):
    d1 = det(l2[0], l2[1], l1[0])
    d2 = det(l2[0], l2[1], l1[1])
    d3 = det(l1[0], l1[1], l2[0])
    d4 = det(l1[0], l1[1], l2[1])
    if d1*d2 <= 0 and d3*d4 <= 0:
        return True
    else:
        return False


def det(p1, p2, p):
    return (p2[0] - p1[0])*(p[1] - p1[1]) - (p2[1] - p1[1])*(p[0] - p1[0])


def get_vector(l):
    return [l[1][0]-l[0][0], l[1][1]-l[0][1]]


def vector_length(a):
    return math.sqrt(a[0]*a[0] + a[1]*a[1])


def vector_mult(a, b):
    return a[0]*b[0]+a[1]*b[1]


def angle(a, b):
    return vector_mult(a,b)/(vector_length(a)*vector_length(b))


def area(p1, p2, p3):
    return abs(det(p1, p2, p3))


def line_length(l):
    return math.sqrt((l[0][0] - l[1][0])*(l[0][0] - l[1][0]) + (l[0][1] - l[1][1])*(l[0][1] - l[1][1]))


def lines_intersect(l1, l2):
    if (l1[0][0]-l1[1][0])/(l2[0][0]-l2[1][0])==(l1[0][1]-l1[1][1])/(l2[0][1]-l2[1][1]):
        return False
    return True


     


def normal(l):
    return [l[1], -l[0]]


