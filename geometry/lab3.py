from polygon import polygon
import lab1


def point_in_convex_polygon(p, p0):
    for i in range(len(p)-1):
        if lab1.point_left([p[i], p[i+1]], p0):
            return False
    return True


def point_out_simple_polygon(p, p0):
    for i in range(len(p)-1):
        if lab1.point_position([p[i], p[i+1]], p0) < 0:
            return True
    return False
