class FileMessage:
    """
    @:param file_full_path 文件在物理机上的全路径，包括文件名
    @:param file_name 文件名
    """
    file_full_path = ""
    file_name = ""

    def __init__(self, file_full_path="", file_name=""):
        self.file_full_path = file_full_path
        self.file_name = file_name


