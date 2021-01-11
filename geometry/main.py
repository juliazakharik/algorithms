from point import point
from line import line
import lab1
from  polygon import polygon, point_in_pol
from point import PointClass as pc
import constants
import draw

def main_1_1():
    p = point.create_point()
    l = line.create_line()
    print(lab1.point_left(l, p))
    draw.draw_point_line(l, p)


def main_1_2():
    l = line.create_line()
    l1 = line.create_line()
    print(lab1.line_segments_intersect(l1, l))
    draw.draw_lines(l1, l)

def main_1_3_4():
    s = 5
    p = polygon.create_polygon(s)
    print("Is simple: ", polygon.is_simple(p))
    print("Is convex: ", polygon.is_convex(p))
    draw.draw_polygon_lab(p)


def main_2():
    p = [[100, 300], [300, 100], [500, 700]]
    p0 = [200, 300]
    #
    #p = [[100, 200], [200, 200], [400, 100]]
    #p0 = [300, 200]

    # p = polygon.create_simple_polygon(5)
    # p0 = point.create_point()

    print(point_in_pol.octan_test(p, p0))
    draw.draw_lab_2(p, p0)


def main_3():
    p = point.create_points(30)
    p1 = polygon.create_convex_polygon_by_grekhem(p)
    p2 = polygon.create_simple_in_convex(p1, 4)
    draw.draw_lab_3(p1, p2, p)


def main_4():
    p = point.create_points(50)
    p1 = polygon.create_convex_polygon_by_grekhem(p)
    draw.draw_lab_4(p1, p)

def main_5():
    p =point.create_points(20)
    draw.draw_lab5(p)


def main_10():
    p =point.create_points(20)
    draw.draw_lab10(p)





def main_6():
    p = point.create_points(30)
    draw.draw_lab_6(p)




main_3()
