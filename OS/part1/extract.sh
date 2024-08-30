# Name: Christopher Miller
# Student ID: 20118392

# Check if the number of arguments is exactly 2
if [ "$#" -ne 2 ]; then
    echo "Error: You must provide exactly 2 arguments."
    echo "Usage: $0 filename directory"
    exit 1
fi

# Assign arguments to variables for readability
filename=$1
directory=$2

# Check if the file exists
if [ ! -f "$filename" ]; then
    echo "Error: The file '$filename' does not exist."
    exit 1
fi

# Check if the directory exists, if not, create it
if [ ! -d "$directory" ]; then
    mkdir -p "$directory"
fi

# Define the output file path
output_file="$directory/special.txt"

# Check if the output file already exists
if [ -f "$output_file" ]; then
    # If it exists, append the lines containing "special"
    grep 'special' "$filename" >> "$output_file"
else
    # If it doesn't exist, create it and add the lines containing "special"
    grep 'special' "$filename" > "$output_file"
fi

echo "Lines containing the word 'special' have been processed and saved to $output_file."
